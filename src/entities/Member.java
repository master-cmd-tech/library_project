package entities;

public class Member {
    private int id;
    private String name;
    private String email;

    public Member() {}

    public Member(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public Member(int id, String name, String email ) {
        this(name, email);
        this.id = id;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }


    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }
}
