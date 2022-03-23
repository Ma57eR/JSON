import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class JsonTest {

    private static final String json = """
                            {
                              "username": "Ivan",
                              "password": "123"
                            }
            """;

    public static void main(String[] args) {


        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        Loga loginData = new Loga("Ivan", "123");

        String s = gson.toJson(loginData);


        Loga loga = gson.fromJson(json, Loga.class);

        System.out.println(s);

        System.out.println("This is from JSON to Loga Class");
        System.out.println(loga);
    }

    static class Loga {
        @Expose
        private String username;
        @Expose
        private String password;

        public Loga(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Loga{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
