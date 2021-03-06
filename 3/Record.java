import java.util.*;

public class Record{
   
public final int id;
private String entity;     
private String relation;
private String property;
private static String wild;
private static int idd=0;

public Record(String entity, String relation, String property){
  this.id=idd;
  this.entity=entity;
  this.relation=relation;
  this.property=property;
  idd++;
}

// Must be unique
public final int id(){             
  return id;
}

// Who
public String entity(){
  return entity;
}

// How
public String relation(){
  return relation;
}

// What
public String property(){
  return property;
}

//Factory method
public static Record makeRecord(String e, String r, String p){
  if(e==null||r==null||p==null){
    throw new IllegalArgumentException();
  } 

  return new Record(e,r,p);
}

//entity appears first and is right justified in a field of 8 characters
//relation appears second and is right justified in a field of 8 characters
//property appears third and is right justified in a field of 10 characters
//id does not apper in toString()
public String toString(){
   String s = String.format("%8s%8s%10s", entity,relation,property);
   return s;
}

//This factory method returns a special kind of record
public static Record makeQuery(String wild, String e, String r, String p){
  Record newRecord;  
  newRecord=new Record(e,r,p);
  newRecord.wild=wild;
  return newRecord;
  }

public boolean entityWild(){
    if(this.entity.equals(wild))
      return true;
    else 
      return false;
}

public boolean relationWild(){
    if(this.relation.equals(wild))
      return true;
    else
      return false;
  }
  
public boolean propertyWild(){
    if(this.property.equals(wild))
      return true;
    else
      return false;
  }

//containsWild() determines if any of the fields entity,relation,property are wild
public boolean containsWild(){
    if(entityWild()||relationWild()||propertyWild())
      return true;
    else
      return false;
  }

//Determine if two Records match
//Matching occurs when all the fields entity,relation,property match
//Fields match if either one or both is wild or both fields are exactly the same according to String.equals()
public boolean matches(Record r){
  int flag=0;
  if(entity.equals(r.entity)||this.entityWild()||r.entityWild())
    flag++;
  if(relation.equals(r.relation)||this.relationWild()||r.relationWild())
    flag++;
  if(property.equals(r.property)||this.propertyWild()||r.propertyWild())
    flag++;
  if(flag==3)
    return true;
  else
    return false;
}

//Each of the comparators compares fields of Records in a different order to determine which Record is sorted first.
//ERPCompare: entity relation property
public static final Comparator<Record> ERPCompare=new Comparator<Record>(){
  public int compare(Record r1, Record r2){
    if(r1.entity.compareTo(r2.entity)==0)
     if(r1.relation.compareTo(r2.relation)==0)
      return r1.property.compareTo(r2.property);
    if(r1.entity.compareTo(r2.entity)==0)
      return r1.relation.compareTo(r2.relation);
    return r1.entity.compareTo(r2.entity);
  }
};

//RPECompare: relation property entity
public static final Comparator<Record> RPECompare=new Comparator<Record>(){
  public int compare(Record r1, Record r2)
  {
    if(r1.relation.compareTo(r2.relation)==0)
     if(r1.property.compareTo(r2.property)==0)
      return r1.entity.compareTo(r2.entity);
    if(r1.relation.compareTo(r2.relation)==0)
      return r1.property.compareTo(r2.property);
    return r1.relation.compareTo(r2.relation);  
  }
};

//PERCompare: property entity relation
public static final Comparator<Record> PERCompare=new Comparator<Record>(){
  public int compare(Record r1, Record r2){
    if(r1.property.compareTo(r2.property)==0)
     if(r1.entity.compareTo(r2.entity)==0)
      return r1.relation.compareTo(r2.relation);
    if(r1.property.compareTo(r2.property)==0)
      return r1.entity.compareTo(r2.entity);
    return r1.property.compareTo(r2.property);
  }
};
}