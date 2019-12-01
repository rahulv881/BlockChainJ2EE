import java.io.*;
import java.util.Arrays;
import java.util.*;
import java.sql.Timestamp;

public class Block implements Serializable{
    private  int previousHash;
    private String data;
    private Timestamp timestamp;
    private int hash;
    
    public Block(String data, int previousHash, int hash)
    {
    	this.data = data;
    	this.previousHash = previousHash;
    	this.hash = hash;
        this.timestamp = new Timestamp(new Date().getTime());

    }
    
    public Block(String data, int previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        // Mix the content of this block with previous hash to create the hash of this new block
        // and that's what makes it block chain
        this.hash  = Arrays.hashCode(new Integer[]{data.hashCode(), previousHash});
    
    this.timestamp = new Timestamp(new Date().getTime());
   
    try{

     //Serialization of Genesis Block;
     FileOutputStream fout;
     if(this.previousHash == 0)
        fout = new FileOutputStream("GenesisBlock.txt");
     else
        fout = new FileOutputStream("/Users/rahul/Downloads/SerializedBlocks/Block: "+this.timestamp+".txt");
       ObjectOutputStream out=new ObjectOutputStream(fout);
       out.writeObject(this);
       out.flush();
       out.close();
       System.out.println("success");

     }catch(Exception e){System.out.println(e);};


   }

   public void setTimestamp(Timestamp t)
   {
     this.timestamp = t;
   }

   public int getPrevHash()
   {
     return previousHash;
   }

   public void setPrevHash(int previoushHash)
   {
	   this.previousHash = previousHash;
   }
   
   public int getHashValue()
   {
     return this.hash;
   }

   public void setHashValue(int hash)
   {
	   this.hash = hash;
   }
   public Timestamp getTimestamp()
   {
     return this.timestamp;
   }

   public int getHash()
   {
     return this.hash;
   }
   public void setHash(int hash)
   {
	   this.hash = hash;
   }
   public int getPreviousHash()
   {
     return this.previousHash;
   }
   
   public void setPreviousHash(int previousHash)
   {
	   this.previousHash = previousHash;
   }
}
