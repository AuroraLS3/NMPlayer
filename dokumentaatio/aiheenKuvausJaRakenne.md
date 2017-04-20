# Aihe
**Aihe:** Musiikkisoitin. Luodaan java pohjainen musiikkisoitin johon voi lisätä mp3 tiedostoja

Musiikkisoittimen soittolistaan voi lisätä biisin plus napilla.
Usean soittolistan luominen on mahdollista.
Soittimessa näkyy nykyisen kappaleen nimi ja pituus.
Play, pause, stop, next ja previous nappien avulla voidaan hallita mitä kappaletta soitetaan.

**Käyttäjät:** Musiikin kuuntelijat

**Toiminnot:**
- Kappaleen toisto (play-, pause- ja stop-napit)
- Kappaleen valinta (seuraava- ja edellinen-napit, listasta valitseminen)
- Soittolistat, vaihtaminen ja uuden luominen
- Kappaleen lisäys ja poisto
- Äänenvoimakkuuden vaihto
- Kappaleen toistokohdan vaihto (seeking)
- "All"-soittolista joka sisältää kaikki lisätyt kappaleet sekä /tracks-kansiossa olevat kappaleet.

**Tiedetyt bugit:**
- Mikäli vedettäviä palkkeja (slider) ei vedetä vaan klikataan jostain kohdasta, muutoksia ei tapahdu - täytyy vetää. (UI)
- Jos MP3-tiesoston ID3-tag on tyhjä, ilmoittaa ohjelma että virhe on tapahtunut ja kirjoittaa virheen ylös.
- Jos JavaFx törmää virheeseen ei NMPlayer saa virhettä napatuksi koska käyttöliittymä pyörii erillisessä langassa.
- "All"-soittolistan kappaleita, joita ei ole lisätty itse soittolistaan ei voi poistaa kuin tilapäisesti. (Ellei poista niitä alkuperäisestä soittolistasta)

### Luokkakaavio
![Luokkakaavio 18.04.2017](https://raw.githubusercontent.com/Rsl1122/NMPlayer/master/dokumentaatio/luokkakaavio18042017.jpg)
**Rakennekuvaus:**
Ohjelma koostuu käyttöliittymästä (vasen) ja logiikasta (oikea).

Käynnistys: Käyttöliittymä käynnistetään ja logiikka käynnistetään käyttöliittymän ollessa valmis. Käyttöliittymän päivitettäviä osia annetaan päälogiikka-luokalle, MusicPlayer.

MusicPlayer käyttää apu-luokkia tiedostojen ja soittolistan muuttamiseen.
- ErrorManager vastaa Errors.txt tiedoston ylläpitämisestä virheiden ylös kirjoittamiseen
- PlaylistFileManager lataa ja tallentaa /playlists kansion tiedostoihin eri kappaleiden tiedostosijainteja.
- TrackFileManager lukee mm. kappaleen nimen ja artistin .wav ja .mp3 tiedostoista sille annettujen sijaintien mukaan. Se luo tiedostojen tiedoista Track olioita, jotka annetaan eteenpäin, mm. PlaylistManager-oliolle.
- MessageSender vastaa käyttäjälle viestinnästä joko komento ikkunaan tai käyttöliittymän TextConsole-olioon.

MusicPlayer ja MessageSender ovat Singleton olioita, jotta niihin käsiksipääseminen olisi helpompaa useammasta luokasta.
Kaikki käyttöliittymän napit hakevat MusicPlayer singletoinin ja käyttävät sen metodeita tarkoitusperänsä aikaansaamiseksi.

Useita käyttöliittymän osia voi päivittää (update). Tätä käytetään MusicPlayer:in tilan kertomiseen mm. nappien tekstien ja värien avulla. Usein napin painaminen päivittää kaikki päivitettävissä olevat osat.
Kappaleen loppuessa MusicPlayer päivittää käyttöliittymän.

Virhetilanteen sattuessa käyttäjälle annetaan viesti.

### Seuraavan raidan valinta, kun raitoja on lisätty vähintään kaksi.
![Sekvenssikaavio, Seuraavan raidan valinta](https://raw.githubusercontent.com/Rsl1122/NMPlayer/master/dokumentaatio/Sekvenssikaavio_nextTrack.jpg)
### Uuden raidan lisäys napin painallus
![Sekvenssikaavio, Uuden raidan lisäys](https://raw.githubusercontent.com/Rsl1122/NMPlayer/master/dokumentaatio/Sekvenssikaavio_AddTrackButton.jpg)
