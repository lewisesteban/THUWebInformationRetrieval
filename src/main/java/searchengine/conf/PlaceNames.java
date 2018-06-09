package searchengine.conf;

import searchengine.document.Document;

public class PlaceNames {

    private static String[][] places = {
            {"Abkhazia", "Abkhaz", "Abkhazian"},
            {"Afghan", "Afghanistan"},
            //{"Åland", "Aland"},
            {"Albania", "Albanian"},
            {"Algeria", "Algerian"},
            {"Samoa", "Samoan"},
            {"Andorra", "Andorran"},
            {"Angola", "Angolan"},
            {"Anguilla", "Anguillan"},
            {"Antarctic", "Antarctica"},
            {"Antigua", "Antiguan"},
            {"Barbuda", "Barbudan"},
            {"Argentina", "Argentinian", "Argentines"},
            {"Armenia", "Armenian"},
            {"Aruba", "Aruban"},
            {"Australia", "Australian", "Aussies"},
            {"Austria", "Austrian"},
            {"Azerbaijan", "Azeri", "Azerbaijani"},
            {"Bahamas", "Bahamian"},
            {"Bahrain", "Bahraini"},
            {"Bangladesh", "Bengali", "Bangladeshis"},
            {"Barbados", "Barbadian"},
            {"Belarus", "Belarusian"},
            {"Belgium", "Belgian"},
            {"Belize", "Belizean"},
            {"Benin", "Beninese", "Beninois"},
            {"Bermuda", "Bermudian", "Bermudan"},
            {"Bhutan", "Bhutanese"},
            {"Bolivia", "Bolivian"},
            {"Bonaire"},
            {"Bosnia", "Herzegovina", "Herzegovinian", "Bosnian"},
            {"Botswana", "Botswanan", "Batswana", "Batswanan", "Motswana", "Motswanan"},
            {"Bouvet"},
            {"Brazil", "Brazilian"},
            {"Brunei", "Bruneian"},
            {"Bulgaria", "Bulgarian"},
            {"Burkina", "Burkinabé", "Burkinabè", "Burkinabe"},
            {"Burma", "Burmese"},
            {"Burundi", "Burundian"},
            {"Cabo Verde", "Cabo Verdean", "carboverde"},
            {"Cambodia", "Cambodian"},
            {"Cameroon", "Cameroonian"},
            {"Canada", "Canadian"},
            {"Cayman", "Caymanian"},
            {"Central Africa", "Central African", "centralafrica"},
            {"Chad", "Chadian"},
            {"Chile", "Chilean"},
            {"China", "Chinese"},
            {"Christmas"},
            {"Cocos", "Keeling"},
            {"Colombia", "Colombian"},
            {"Comoros", "Comoran", "Comorian"},
            {"Congo", "Congolese"},
            {"Cook Island", "Cook Islander"},
            {"Costa Rica", "Costa Rican"},
            {"Côte d'Ivoire", "Cote d'Ivoire", "Ivory Coast", "Ivorian"},
            {"Croatia", "Croatian", "Croats"},
            {"Cuba", "Cuban"},
            {"Curaçao", "Curaçaoan", "Curacao", "Curacaoan"},
            {"Cyprus", "Cypriot"},
            {"Czech"},
            {"Denmark", "Danish", "Danes"},
            {"Djibouti", "Djiboutian"},
            {"Dominica", "Dominican"},
            {"Timor", "Timorese"},
            {"Ecuador", "Ecuadorian"},
            {"Egypt", "Egyptian"},
            {"Salvador", "Salvadoran"},
            {"Equatorial Guinea", "Equatoguinean", "Equatorial Guinean"},
            {"Eritrea", "Eritrea"},
            {"Estonia", "Estonian"},
            {"Ethiopia", "Ethiopian"},
            {"Falkland"},
            {"Faroe", "Faroese"},
            {"Fiji", "Fijian"},
            {"Finland", "Finnish", "Finns"},
            {"France", "French"},
            {"Guiana", "Guianese"},
            {"Polynesia", "Polynesian"},
            {"Gabon", "Gabonese", "Gabonaise"},
            {"Gambia", "Gambian"},
            {"Georgia", "Georgian"},
            {"German", "Germany"},
            {"Ghana", "Ghanaian"},
            {"Gibraltar", "Gibraltarian"},
            {"Greece", "Greek", "Hellenic", "Hellenes"},
            {"Greenland", "Greenlandic", "Greenlander"},
            {"Grenada", "Grenadian"},
            {"Guadeloupe", "Guadeloupian"},
            {"Guam", "Guamanian"},
            {"Guatemala", "Guatemalan"},
            {"Guernsey", "Jersey", "Channel Island", "Channel Islander"},
            {"Guinea-Bissau", "Bissau-Guinean"},
            {"Guinea", "Guinean"},
            {"Guyana", "Guyanese"},
            {"Haiti", "Haitian"},
            {"Heard", "McDonald"},
            {"Honduras", "Honduran"},
            {"Hong Kong", "Hong Kongese", "Hong Konger", "hongkong"},
            {"Hungary", "Hungarian", "Magyar"},
            {"Iceland", "Icelandic", "Icelander"},
            {"India", "Indian"},
            {"Indonesia", "Indonesian"},
            {"Iran", "Iranian", "Persian"},
            {"Iraq", "Iraqi"},
            {"Ireland", "Irish"},
            {"Isle of Man", "Manx"},
            {"Israel", "Israeli"},
            {"Italy", "Italian"},
            {"Jamaica", "Jamaican"},
            {"Jan Mayen"},
            {"Japan", "Japanese"},
            {"Jordan", "Jordanian"},
            {"Kazakhstan", "Kazakhstani", "Kazakh"},
            {"Kenya", "Kenyan"},
            {"Kiribati", "I-Kiribati"},
            {"North Korea", "North Korean"},
            {"South Korea", "South Korean"},
            {"Korea", "Korean"},
            {"Kosovo", "Kosovar", "Kosovan"},
            {"Kuwait", "Kuwaiti"},
            {"Kyrgyzstan", "Kyrgyzstani", "Kyrgyz", "Kirgiz", "Kirghiz"},
            {"Laos", "Lao", "Laotian"},
            {"Latvia", "Latvian", "Lettish", "Letts"},
            {"Lebanon", "Lebanese"},
            {"Lesotho", "Basotho", "Mosotho"},
            {"Liberia", "Liberian"},
            {"Libya", "Libyan"},
            {"Liechtenstein", "Liechtensteiner"},
            {"Lithuania", "Lithuanian"},
            {"Luxembourg", "Luxembourgish", "Luxembourger"},
            {"Macau", "Macanese"},
            {"Macedonia", "Macedonian"},
            {"Madagascar", "Malagasy"},
            {"Malawi", "Malawian"},
            {"Malaysia", "Malaysian"},
            {"Maldives", "Maldivian"},
            {"Mali", "Malian", "Malinese"},
            {"Malta", "Maltese"},
            {"Marshall"},
            {"Martinique", "Martiniquais", "Martinican"},
            {"Mauritania", "Mauritanian"},
            {"Mauritius", "Mauritian"},
            {"Mayotte", "Mahoran"},
            {"Mexico", "Mexican"},
            {"Micronesia", "Micronesian"},
            {"Moldova", "Moldovan"},
            {"Monaco", "Monégasque", "Monegasque", "Monacan"},
            {"Mongolia", "Mongolian", "Mongol"},
            {"Montenegro", "Montenegrin"},
            {"Montserrat", "Montserratian"},
            {"Morocco", "Moroccan"},
            {"Mozambique", "Mozambican"},
            {"Myanmar", "Burmese", "Bamar"},
            {"Namibia", "Namibian"},
            {"Nauru", "Nauruan"},
            {"Nepal", "Nepali", "Nepalese"},
            {"Netherlands", "Netherlandic", "Dutch"},
            {"New Caledonia", "New Caledonian"},
            {"New Zealand", "N.Z.", "Zelanian", "New Zealanders", "newzealand"},
            {"Nicaragua", "Nicaraguan"},
            {"Niger", "Nigerien"},
            {"Nigeria", "Nigerian"},
            {"Niue", "Niuean"},
            {"Norfolk"},
            {"Northern Ireland", "Northern Irish"},
            {"Northern Mariana", "Northern Marianan"},
            {"Norway", "Norwegian"},
            //{"Oman", "Omani"},
            {"Pakistan", "Pakistani", "Pakis"},
            {"Palau", "Palauan"},
            {"Palestine", "Palestinian"},
            {"Panama", "Panamanian"},
            {"Papua New Guinea", "Papua New Guinean", "Papuan"},
            {"Paraguay", "Paraguayan"},
            {"Peru", "Peruvian"},
            {"Filipino", "Philippine"},
            {"Pitcairn"},
            {"Poland", "Polish", "Poles"},
            {"Portugal", "Portuguese"},
            {"Puerto Rico", "Puerto Rican", "puertorico", "puertorican"},
            {"Qatar", "Qatari"},
            {"Réunion", "Reunion", "Reunionais", "Réunionais", "Reunionese", "Réunionese"},
            {"Romania", "Romanian"},
            {"Russia", "Russian"},
            {"Rwanda", "Rwandan", "Banyarwanda"},
            {"Saba"},
            {"Saint Barthélemy", "Saint Barthelemy", "Barthélemois", "Barthelemois"},
            {"Saint Helena", "Saint Helenian"},
            {"Saint Kitts and Nevis", "Kittitian", "Nevisian"},
            {"Saint Lucia", "Saint Lucian"},
            {"Saint Martin", "Saint Martinoise"},
            {"Saint Pierre and Miquelon", "Saint-Pierrais", "Miquelonnais"},
            {"Saint Vincent and the Grenadines", "Vincentian"},
            {"Samoa", "Samoan"},
            {"San Marino", "Sammarinese", "sanmarino"},
            {"São Tomé", "Sao Tome", "São Toméan", "Sao Tomean", "saotome"},
            {"Saudi"},
            {"Scotland", "Scottish"},
            {"Senegal", "Senegalese"},
            {"Serbia", "Serbian", "Serbs"},
            {"Seychelles", "Seychellois"},
            {"Sierra Leone", "Sierra Leonean", "sierraleone"},
            {"Singapore", "Singaporean"},
            {"Sint Eustatius", "Statian"},
            {"Sint Maarten", "Sint Maartener"},
            {"Slovak", "Slovakia"},
            {"Slovenia", "Slovenian", "Slovene"},
            {"Solomon"},
            {"Somaliland", "Somalilander"},
            {"Somali", "Somalia"},
            {"South Africa", "South African", "southafrica"},
            {"South Georgia", "South Sandwich"},
            {"South Ossetia", "South Ossetian"},
            {"South Sudan", "South Sudanese"},
            {"Spain", "Spanish", "Spaniards"},
            {"Sri Lanka", "Sri Lankan"},
            {"Suda", "Sudan", "Sudanese"},
            {"Surinam", "Surinamese", "Surinamer"},
            {"Svalbard"},
            {"Swazi", "Swaziland"},
            {"Sweden", "Swedish", "Swedes"},
            {"Switzerland", "Swiss"},
            {"Syria", "Syrian"},
            {"Taiwan", "Taiwanese"},
            {"Tajikistan", "Tajikistani", "Tajiks"},
            {"Tanzania", "Tanzanian"},
            {"Thai", "Thailand"},
            {"Timor-Leste", "Timorese"},
            {"Togo", "Togolese"},
            {"Tokelau", "Tokelauan"},
            {"Tonga", "Tongan"},
            {"Trinidad", "Trinidadian", "Trinis"},
            {"Tobago", "Tobagonian"},
            {"Tunisia", "Tunisian"},
            {"Turkey", "Turkish", "Turks"},
            {"Turkmen", "Turkmenistan"},
            {"Turks and Caicos Island", "Turks and Caicos Islander"},
            {"Tuvalu", "Tuvaluan"},
            {"Uganda", "Ugandan"},
            {"Ukraine", "Ukrainian"},
            {"United Arab Emirates", "Emirati", "Emirian", "Emiri", "arabia"},
            {"United Kingdom", "unitedkingdom", "Great Britain", "greatbritain", "British", "the UK", "Britons", "U.K.", "G.B.", "England", "English"},
            {"United States of America", "America", "American", "U.S.", "U.S.A."},
            {"Uruguay", "Uruguayan"},
            {"Uzbekistan", "Uzbekistani", "Uzbek"},
            {"Vanuatu", "Ni-Vanuatu", "Vanuatuan"},
            {"Vatican"},
            {"Venezuela", "Venezuelan"},
            {"Vietnam", "Vietnamese"},
            {"Virgin Island", "Virgin Islander"},
            {"Futuna", "Futunan"},
            {"Wallis", "Wallisian"},
            {"Sahara", "Sahrawi", "Sahrawian", "Sahraouian", "Sahrawis", "Sahraouis"},
            {"Yemen", "Yemeni"},
            {"Zambia", "Zambian"},
            {"Zimbabwe", "Zimbabwean", "Zimbos"}
    };

    static {
        for (String[] placeNames : places) {
            for (int i = 0; i < placeNames.length; ++i) {
                placeNames[i] = placeNames[i].toLowerCase();
            }
        }
    }

    private static WordGraph[] placeNameFindersExcluding = new WordGraph[places.length];

    static {
        for (int i = 0; i < places.length; ++i) {
            placeNameFindersExcluding[i] = new WordGraph(1);
            for (int j = 0; j < places.length; ++j) {
                if (i != j) {
                    for (String placeName : places[j]) {
                        placeNameFindersExcluding[i].feed(placeName, 1);
                    }
                }
            }
        }
    }

    private static WordGraph fullPlaceNamesGraph = new WordGraph(1);

    static {
        for (String[] placeNames : places) {
            for (String placeName : placeNames) {
                fullPlaceNamesGraph.feed(placeName, 1);
            }
        }
    }

    private static int getIndex(String name) {
        name = name.toLowerCase();
        for (int i = 0; i < places.length; ++i) {
            for (int j = 0; j < places[i].length; ++j) {
                if (places[i][j].equals(name)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean isAcceptable(String name) {
        return getIndex(name) != -1;
    }

    public static String[] getOptions(String name) {
        return places[getIndex(name)];
    }

    static boolean isFromOneCountry(Document doc) {
        System.out.println("Is recipe. Is from one country?");
        return countDifferentPlaceUpTo2(doc.getTitle()) == 1
                || countDifferentPlaceUpTo2(doc.getUrl().replace('-', ' ')) == 1
                || countDifferentPlaceUpTo2(doc.getContent()) == 1;
    }

    private static int countDifferentPlaceUpTo2(String str) {
        String firstPlace = fullPlaceNamesGraph.findFirst(str);
        if (firstPlace == null) {
            System.out.println("NbPlaces=0");
            return 0;
        }
        int firstPlaceIndex = getIndex(firstPlace);
        if (placeNameFindersExcluding[firstPlaceIndex].findFirst(str) == null) {
            System.out.println("NbPlaces=1 (" + firstPlace + ")");
            return 1;
        } else {
            System.out.println("NbPlaces=2");
            return 2;
        }
    }
}
