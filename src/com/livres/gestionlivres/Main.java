package com.livres.gestionlivres;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionnaireLivre gestionnaire = new GestionnaireLivre();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher tous les livres");
            System.out.println("3. Rechercher un livre par titre");
            System.out.println("4. Modifier un livre");
            System.out.println("5. Supprimer un livre");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();  // consommer le saut de ligne

            switch (choix) {
                case 1:
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.print("ISBN : ");
                    String isbn = scanner.nextLine();
                    System.out.print("Année de publication : ");
                    int annee = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Genre : ");
                    String genre = scanner.nextLine();
                    Livre nouveauLivre = new Livre(titre, auteur, isbn, annee, genre);
                    gestionnaire.ajouterLivre(nouveauLivre);
                    break;

                case 2:
                    List<Livre> livres = gestionnaire.getTousLesLivres();
                    for (Livre l : livres) {
                        System.out.println(l);
                    }
                    break;

                case 3:
                    System.out.print("Titre à rechercher : ");
                    String titreRecherche = scanner.nextLine();
                    Livre livreTrouve = gestionnaire.rechercherLivreParTitre(titreRecherche);
                    if (livreTrouve != null) {
                        System.out.println("Livre trouvé : " + livreTrouve);
                    } else {
                        System.out.println("Aucun livre trouvé avec ce titre.");
                    }
                    break;

                case 4:
                    System.out.print("ID du livre à modifier : ");
                    int idModif = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau titre : ");
                    String titreModif = scanner.nextLine();
                    System.out.print("Nouvel auteur : ");
                    String auteurModif = scanner.nextLine();
                    System.out.print("Nouveau ISBN : ");
                    String isbnModif = scanner.nextLine();
                    System.out.print("Nouvelle année : ");
                    int anneeModif = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau genre : ");
                    String genreModif = scanner.nextLine();
                    Livre livreModifie = new Livre(idModif, titreModif, auteurModif, isbnModif, anneeModif, genreModif);
                    gestionnaire.modifierLivre(livreModifie);
                    break;

                case 5:
                    System.out.print("ID du livre à supprimer : ");
                    int idSupprimer = scanner.nextInt();
                    scanner.nextLine();
                    gestionnaire.supprimerLivre(idSupprimer);
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        } while (choix != 0);


    }
}

