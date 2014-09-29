package uima.collectionReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
/**
 * read input file line by line
 * @author jessyli
 *
 */

public class CollectionReader extends CollectionReader_ImplBase {
  static final String PARAM_INPUT_FILE = "Inputdatasourse";

  BufferedReader reader = null;

  String line = null;

  @Override
  public void initialize() throws ResourceInitializationException {
    String filePath = ((String) getConfigParameterValue(PARAM_INPUT_FILE)).trim();
    // URL resource = CollectionReader.class.getClassLoader().getResource(filePath);
    try {
      // reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(
      // resource.getFile())), "utf-8"));
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),
              "utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    super.initialize();
  }

  @Override
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    // TODO Auto-generated method stub
    aCAS.setDocumentText(line);
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    // TODO Auto-generated method stub
    line = reader.readLine();
    return line != null;
  }

  @Override
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void close() throws IOException {
    // TODO Auto-generated method stub
    reader.close();
  }

}
