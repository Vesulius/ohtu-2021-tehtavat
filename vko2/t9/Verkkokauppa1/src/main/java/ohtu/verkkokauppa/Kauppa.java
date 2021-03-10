package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {
    @Autowired
    private VarastoInterface varastoInf;
    @Autowired
    private PankkiInterface pankkiInf;
    private Ostoskori ostoskori;
    @Autowired
    private ViitegeneraattoriInterface viitegeneraattoriInf;
    private String kaupanTili;

    public Kauppa() {
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varastoInf.haeTuote(id); 
        varastoInf.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varastoInf.saldo(id)>0) {
            Tuote t = varastoInf.haeTuote(id);             
            ostoskori.lisaa(t);
            varastoInf.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattoriInf.uusi();
        int summa = ostoskori.hinta();
        
        return pankkiInf.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
