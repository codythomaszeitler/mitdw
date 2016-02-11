/**
 * Created by Cody Thomas Zeitler on 12/7/2015.
 */
public class Tester {

    int a;

    void setA(int a){

        this.a = a;

    }

    int getA(){

       return a;

    }
    public enum Boom {

        ONE("one string"), TWO("two string"), THREE("three sting"), FOUR("four string"), FIVE("five string");


        private String temp;

        private Boom (String temp){

            this.temp = temp;

        }

        public String getString(){

            return temp;

        }

        public int integer;



    }




}




