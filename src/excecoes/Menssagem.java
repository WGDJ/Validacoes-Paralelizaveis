package excecoes;

public class Menssagem extends Exception {

    private String menssagem;

    Menssagem(String menssagem) {
        this.setMenssagem(menssagem);
    }

    @Override
    public String getMessage(){
        return this.getMenssagem();
    }


    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }
}
