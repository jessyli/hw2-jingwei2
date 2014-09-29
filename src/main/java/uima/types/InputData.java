

/* First created by JCasGen Fri Sep 26 14:18:10 EDT 2014 */
package uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Sep 29 06:04:23 EDT 2014
 * XML source: /home/jessyli/workspace/hw2-jingwei2/src/main/resources/xml/TypeSystem.xml
 * @generated */
public class InputData extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(InputData.class);
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
  protected InputData() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public InputData(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public InputData(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public InputData(JCas jcas, int begin, int end) {
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
  //* Feature: content

  /** getter for content - gets 
   * @generated
   * @return value of the feature 
   */
  public String getContent() {
    if (InputData_Type.featOkTst && ((InputData_Type)jcasType).casFeat_content == null)
      jcasType.jcas.throwFeatMissing("content", "uima.types.InputData");
    return jcasType.ll_cas.ll_getStringValue(addr, ((InputData_Type)jcasType).casFeatCode_content);}
    
  /** setter for content - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContent(String v) {
    if (InputData_Type.featOkTst && ((InputData_Type)jcasType).casFeat_content == null)
      jcasType.jcas.throwFeatMissing("content", "uima.types.InputData");
    jcasType.ll_cas.ll_setStringValue(addr, ((InputData_Type)jcasType).casFeatCode_content, v);}    
   
    
  //*--------------*
  //* Feature: key

  /** getter for key - gets 
   * @generated
   * @return value of the feature 
   */
  public String getKey() {
    if (InputData_Type.featOkTst && ((InputData_Type)jcasType).casFeat_key == null)
      jcasType.jcas.throwFeatMissing("key", "uima.types.InputData");
    return jcasType.ll_cas.ll_getStringValue(addr, ((InputData_Type)jcasType).casFeatCode_key);}
    
  /** setter for key - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setKey(String v) {
    if (InputData_Type.featOkTst && ((InputData_Type)jcasType).casFeat_key == null)
      jcasType.jcas.throwFeatMissing("key", "uima.types.InputData");
    jcasType.ll_cas.ll_setStringValue(addr, ((InputData_Type)jcasType).casFeatCode_key, v);}    
  }

    