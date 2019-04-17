public class OracleTeacher extends Teacher {
    public OracleTeacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void p() {
        System.out.println("ID:" + this.id);
        System.out.println("name:" + this.name);
    }

    public static void main(String[] args) {
        OracleTeacher oracleTeacher = new OracleTeacher("001","jack");
        oracleTeacher.p();
    }
}
