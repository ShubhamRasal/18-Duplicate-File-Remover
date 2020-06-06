import java.lang.*;
import java.io.*;
import java.security.*;
import java.util.*;


public class Delete_Duplicate
{
    HashMap<String,String> hobj=new HashMap<String,String>();
    LinkedList lobj=new LinkedList();
    String str=null;
    Delete_Duplicate()
    {
    
    }
    public boolean add_chksum(String filepath)throws Exception
    {
        String result=null;
        try{
                result=checksum(filepath);             // call to method which return checksum of file
            }catch(IOException eobj)
            {
                    System.out.println("unbale to find checkSum of file \n"+eobj);            
            }
          
          if(!hobj.containsKey(result))           // if checksum is not already available then goes in if
            {                                             // filter for not copy duplicate file <data_wise>
                hobj.put(result,filepath);
               // System.out.println(result);
            return true;
            }
            else                                                        // return false if checksum is duplicated
             {
                 str=hobj.get(result);

                //    System.out.println("duplicate file :"+filepath+"  ,"+hobj.get(result));
            return false;
             }
    }

    public String checksum(String filepath) throws IOException ,NoSuchAlgorithmException{

        MessageDigest md=MessageDigest.getInstance("MD5");
        // DigestInputStream is better, but you also can hash file like this.
        try (InputStream fis = new FileInputStream(filepath)) {
            byte[] buffer = new byte[1024];
            int nread;
            while ((nread = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();

    }


    public void list(String dname ) throws Exception
    {
        File folder=new File(dname);
        String filepath=folder.getAbsolutePath();
        if(!folder.exists())
        {
            System.out.println("Folder does not exists!!");
            return ;
        }
        
        String arr[]=folder.list();
        for(int i=0;i<arr.length;i++)
        {
            File fd=new File(filepath.concat("\\"+arr[i]));
                if(fd.isDirectory())
                {
                    System.out.println("FOLDER:"+fd.getName());
                   list(fd.getAbsolutePath());
                }else
                if(fd.isFile())
                {
                    if((add_chksum(fd.getAbsolutePath())==false)&& fd.delete())
                    {
                         System.out.println("file deleted           :"+str+"    "+fd.getName());
                         lobj.add(str+"=>"+fd.getName());
                    }
               
                }
            
        }//for closed
    }
    public LinkedList getLlist()
    {
        return lobj;
    }

    public static void main(String args[])  throws Exception
    {
        chksum cobj=new chksum();
        //System.out.println(cobj.add_chksum("longest_word.java"));
        //cobj.add_chksum("longest_word.java");
        //cobj.add_chksum("longest_word.java");
        cobj.list("java");
    }

    
}