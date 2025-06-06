public class Dame extends Piece {

    public Dame(String couleur){

        super(couleur);
    }

    public boolean deplacement(Case destination){

        int colonneDepart = Echiquier.indiceEtiquette(this.getCase().getEtiquette());
        int colonneArrivee = Echiquier.indiceEtiquette(destination.getEtiquette());
        int ligneDepart = Echiquier.indiceNumero(this.getCase().getNumero());
        int ligneArrivee = Echiquier.indiceNumero(destination.getNumero());

        boolean deplacementHorizontalOuVertical =  colonneDepart == colonneArrivee || ligneDepart == ligneArrivee;
        boolean deplacementDiagonale = Math.abs(colonneArrivee - colonneDepart) == Math.abs(ligneArrivee - ligneDepart);

        return deplacementHorizontalOuVertical || deplacementDiagonale;
}


    public String toString(){

        if(this.getCouleur().equals("blanc"))
            return " d " ;
        return " D ";
    }

}
