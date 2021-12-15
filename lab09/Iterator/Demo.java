package Iterator;

import java.util.Iterator;
import java.util.ListIterator;

public class Demo {

   public static void main(String[] args) {
       VectorGeneric<String> vs = new VectorGeneric<>();

       for (int i = 0; i <10; i++){
           vs.addElem(new String("olá " +i));
       }
       Iterator<String> vsi = vs.iterator();
       while (vsi.hasNext()){
           System.out.println(vsi.next());
       }

       VectorGeneric<String> vs2 = new VectorGeneric<>();

       for (int i = 0; i <10; i++){
           vs2.addElem(new String("adeus " +i));
       }

       ListIterator<String> vsl = vs2.listIterator(1);
       while (vsl.hasPrevious())
           System.out.println(vsl.previous());
   }
}
