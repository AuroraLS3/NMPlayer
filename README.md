# NMPlayer

Tavoitteena on luoda helppokäyttöinen musiikkisoitin. Soittimeen voi lisätä mp3 tiedostoja mistä tahansa kansiosta.
Soittimella pystyy myös tekemään useamman soittolistan.


## Dokumentaatio
[Aiheen kuvaus](dokumentaatio/aiheenKuvausJaRakenne.md)  
[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)  
[Pit-raportti](https://htmlpreview.github.io/?https://github.com/Rsl1122/NMPlayer/blob/master/dokumentaatio/pit/index.html)  
[Checkstyle-raportti](https://htmlpreview.github.io/?https://github.com/Rsl1122/NMPlayer/blob/master/dokumentaatio/checkstyle/checkstyle.html)
[Javadoc](https://htmlpreview.github.io/?https://github.com/Rsl1122/NMPlayer/blob/master/javadoc/index.html)
Checkstyle-raportin yksi virhe aiheutuu Javadocin pituudesta luokassa. Ilman javadocia luokan pituus olisi ~150.

### Käyttöohjeet
- Ohjelman voi käynnistää tuplaklikkaamalla .jar tiedostoa.
- Lisätäksesi kappale paina "Add Tracks"-nappia ja lisää mp3 tai wav tiedosto(ja). Kappaleet lisätään valittuna olevaan soittolistaan.
- Ohjelman nimi-palkki kertoo soiko musiikki, mikä soittolista on valittu, sekä valitun kappaleen artistin ja nimen.
- Valittu kappale on värjätty siniseksi soittolistassa.
- Kappaleen voi valita painamalla kappaleen nimeä listassa tai '<<' ja '>>' napeilla.
- Soittolistaa voi vaihtaa kirjoittamalla pieneen tekstiboksiin soittolistan nimen ja painamalla 'Enter' tai "Change Playlist"-nappia. Jos boksi on tyhjä, kertoo ohjelma tiedossa olevat soittolistat.
- Kappaleen voi poistaa "X"-napista kappaleen vieressä.
