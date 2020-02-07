package modele;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String dateN;
    private String adress;
    private String passWord;
    private String email;
    private boolean compteloque;
    private String photo;
    private String dateInsecription;

    public String getDateInsecription() {
        return dateInsecription;
    }

    public void setDateInsecription(String dateInsecription) {
        this.dateInsecription = dateInsecription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDocRes() {
        return docRes;
    }

    public void setDocRes(String docRes) {
        this.docRes = docRes;
    }

    public String getPhotoidenti() {
        return photoidenti;
    }

    public void setPhotoidenti(String photoidenti) {
        this.photoidenti = photoidenti;
    }

    private String docRes;
    private String photoidenti;

    public Membre() {
    }

    public boolean isCompteloque() {
        return compteloque;
    }

    public void setCompteloque(boolean compteloque) {
        this.compteloque = compteloque;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateN() {
        return dateN;
    }

    public void setDateN(String dateN) {
        this.dateN = dateN;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
