import java.util.Arrays;

public class MusicRunner
{
  public static String Clean (String str)
  {
    return str.substring(1, str.length()-1).trim();
  }
  public static void main (String[] args)
  {
    int count = 0;
    MusicReader mr = new MusicReader();
    
    mr.open("musiclist.csv");
    
    String[] data = mr.getSongData();
    
    // First line contains all the fields - We don't want to save this anywhere but we can
    // print it for now to see what information we have.
    System.out.println(Arrays.toString(data));
    
    data = mr.getSongData();  // Get next line of song data
    
    // if data is null then we were unable to read a line of song data, so
    // this loop will continue to read lines of song data as long as there
    // IS song data available
    while (data != null)
    {
// You probably will comment this out but for now print out the line so you can see what is there
      System.out.println(Arrays.toString(data));
      
      int year = Integer.parseInt(Clean(data[3]));
      double score = Double.parseDouble(Clean(data[4]));
      String notes = (Clean(data[16]));
      // Let's try to create a Song object
      Song song = new Song(Clean(data[0]), Clean(data[1]), year, score, notes);  // data[0] is the artist and data[1] is the name
      System.out.println(Clean(data[0]) + ", " + Clean(data[1]) + ", " + year + ", " + score + ", " + notes);
      count++;
      
      if (count == 10)  // For now only read ONE song
        break;
      
      data = mr.getSongData();  // Get next line of song data
    }
    
    mr.close();
  }
}