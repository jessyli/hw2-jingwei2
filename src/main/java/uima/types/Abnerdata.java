

/* First created by JCasGen Sat Sep 27 11:52:22 EDT 2014 */
package uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Sep 29 06:04:23 EDT 2014
 * XML source: /home/jessyli/workspace/hw2-jingwei2/src/main/resources/xml/TypeSystem.xml
 * @generated */
public class Abnerdata extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Abnerdata.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Abnerdata() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Abnerdata(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Abnerdata(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Abnerdata(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: gene

  /** getter for gene - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGene() {
    if (Abnerdata_Type.featOkTst && ((Abnerdata_Type)jcasType).casFeat_gene == null)
      jcasType.jcas.throwFeatMissing("gene", "uima.types.Abnerdata");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Abnerdata_Type)jcasType).casFeatCode_gene);}
    
  /** setter for gene - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGene(String v) {
    if (Abnerdata_Type.featOkTst && ((Abnerdata_Type)jcasType).casFeat_gene == null)
      jcasType.jcas.throwFeatMissing("gene", "uima.types.Abnerdata");
    jcasType.ll_cas.ll_setStringValue(addr, ((Abnerdata_Type)jcasType).casFeatCode_gene, v);}    
  }

    