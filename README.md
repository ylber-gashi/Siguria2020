# Siguria2020 - Grupi 15



Në këtë projekt janë realizuar 11 komanda kryesore (3 nga faza e parë, 6 nga faza e dytë dhe 2 tjera nga faza e tretë) të cilat kanë edhe funksione(nënkomanda) tjera. Realizimin e tyre e kemi bërë me anë të gjuhës programuese Java.



## Komandat kryesore me funksionet apo nënkomandat përkatëse janë: 


### FAZA 1
1. **morse-code**
*   **encode**  (Alfabetin latin e kthen në Morse kod)
*   **decode** (Hyrjen si Morse kod e kthen në alfabet latin)
*   **beep**  (Hyrjen si alfabet latin e kthen në Morse kod dhe e shpreh me zë, për secilin karakter)
2. **caesar**
*   **encrypt** (Çdo karakter të tekstit hyrës e zhvendos për aq pozita sa është dhënë çelesi)
*   **decrypt** (Cipher tekstin e zhvendos për aq sa është dhënë çelesi, por në anën e kundërt në krahasim me enkriptimin)
*   **brute-force** (Provon 25 herë dekriptimin e një cipher teksti të dhënë, me 25 çelësa të ndryshëm)
3. **count**
*   **lines** (Numëron rreshtat në tekstin hyrës)
*   **words** (Numëron fjalët në tekstin hyrës)
*   **letters** (Numëron shkronjat në tekstin hyrës)
*   **symbols** (Numëron simbolet në tekstin hyrës, pra pa i përfshirë numrat, shkronjat dhe hapësirat)
*   **characters** (Numëron të gjitha karakteret në tekstin hyrës, anashkalohen vetëm hapësirat)
*   **vowels** (Numëron zanoret në tekstin hyrës)
*   **consonants** (Numëron bashkëtingëlloret në tekstin hyrës duke i zbritur numrin e zanoreve, hapësirave dhe simboleve nga gjatësia e tekstit hyrës)
*   **spaces**(Numëron hapësirat në tekstin hyrës)

### FAZA 2
1.
*	**create-user** (Krijon një çift të publik/privat të RSA me emrat <name>.xml dhe <name>.pub.xml brenda
direktoriumittëçelësave keys)
*	**delete-user** (I largon të gjithë çelësat ekzistues të shfrytëzuesit.)
2. 
*	**export-key** (Eksporton çelësin publik ose privat të shfrytëzuesit nga direktoriumi i çelësave.)
*	**import-key** (Importon çelësin publik ose privat të shfrytëzuesit nga shtegu i dhënë dhe e vendos në direktoriumin e çelësave.)
3.
*	**write-message** (E shkruan një mesazh të enkriptuar të dedikuar për një shfrytëzues.)
*	**read-message** (E dekripton dhe e shfaq në console mesazhin e enkriptuar.)

### FAZA 3
1.
*	**create-user** (Kjo komandë është zgjeruar ashtu që gjatë krijimit të shfrytëzuesit të kërkohet edhe
fjalëkalimi.)
*	**delete-user** (I largon të gjithë çelësat ekzistues të shfrytëzuesit dhe e fshin shfrytëzuesin nga databaza.)
2. 
*	**login** (Teston çiftin shfrytëzues/fjalëkalim. Në rast suksesi lëshohet një token i nënshkruar i cili mund të
përdoret për autentikim të shfrytëzuesit.)
*	**status** (Jep informata rreth tokenit.)
3.
*	**write-message** (Kjo komandë është zgjeruar ashtu që të mund ta pranojë edhe opsionin --sender <token>.)
*	**read-message** (Kjo komandë është zgjeruar ashtu që nëse figuron pjesa e dërguesit/nënshkrimit në mesazh,
atëherë do të tentohet verifikimi i atij nënshkrimi duke përdorur çelësin publik të dërguesit.)

## Për ekzekutimin e programit duhet ti ndjekni këto hapa:


1. Së pari e bëni compile duke përdorur komandën: 
	**javac paketa\ds.java**
(sigurohuni të jeni në path-in e duhur)
2. Së dyti, për të ekzekutuar një nga funksionet e programit duhet t’i shkruani komandat në këtë format: 

### Faza 1

-Per funksionin **_morse-code_**:

	java paketa.ds morse-code encode/decode <text>/<morse code>

	java paketa.ds morse-code beep <text>


-Per funksionin **_caesar_**:

	java paketa.ds caesar encrypt/decrypt <key> <plain text>/<cipher text>

	java paketa.ds caesar brute-force <cipher text>


-Per funksionin **_count_**:

	java paketa.ds count lines/words/letters/symbols <text>

	java paketa.ds count characters/vowels/consonants/spaces <text>
    
    
### Faza 2

-Per komandën **_create-user_**:
	
	java paketa.ds create-user <name>
	
-Per komandën **_delete-user_**:

	java paketa.ds delete-user <name>
	
-Per komandën **_export-key_**:

	java paketa.ds export-key <public|private> <name> [file]
	
-Per komandën **_import-key_**:

	java paketa.ds import-key <name> <path>
	
-Per komandën **_write-message_**:

	java paketa.ds write-message <name> <message> [file]
	
-Per komandën **_read-message_**:

	java paketa.ds read-message <encrypted-message>

### FAZA 3
-Per komandën **_create-user_**:
	
	java paketa.ds create-user <name>
	
-Per komandën **_delete-user_**:

	java paketa.ds delete-user <name>
	
-Per komandën **login**:

	java paketa.ds login <name>
	
-Per komandën **status**:

	java paketa.ds status <token>
	
-Per komandën **_write-message_**:

	java paketa.ds write-message <name> <message> [file] --sender <token>
	
-Per komandën **_read-message_**:

	java paketa.ds read-message <encrypted-message>
	
## Shembuj të përdorimit të komandave:
### FAZA 1

	morse-code
  
  ![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/1.PNG)

    



	caesar

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/2.PNG)


    




	count

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/3.PNG)


    




### FAZA 2
	create-user

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/4.PNG)


    




	delete-user

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/5.PNG)


    




	export-key

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/6.PNG)    


    




	import-key

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/7.PNG)


    




	write-message

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/8.PNG)


    




	read-message

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/9.PNG)


### FAZA 3
	create-user

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/10.PNG)


    




	delete-user

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/11.PNG)


    




	login

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/12.PNG)    


    




	status

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/13.PNG)


    




	write-message

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/14.PNG)


    




	read-message

![](https://github.com/ylber-gashi/Siguria2020/blob/master/Images/15.PNG)


## Referencat:



1. [https://gist.github.com/Xyene/6478305?fbclid=IwAR3nkyPECY5aaOrOUDaYuoaWTlB5cCj8P6q2ZjaKqWYDydgeba2AQGK4_qc](https://gist.github.com/Xyene/6478305?fbclid=IwAR3nkyPECY5aaOrOUDaYuoaWTlB5cCj8P6q2ZjaKqWYDydgeba2AQGK4_qc) (Nga ku jemi bazuar për funksionin beep tek morse code)

2. [https://www.thejavaprogrammer.com/caesar-cipher-java-encryption-decryption/?fbclid=IwAR2gvwtkMhrTBIQ4C6wxrD55jYZhD2kNldBwvgDVLd3jxFpsvJMfuMA0QUM](https://www.thejavaprogrammer.com/caesar-cipher-java-encryption-decryption/?fbclid=IwAR2gvwtkMhrTBIQ4C6wxrD55jYZhD2kNldBwvgDVLd3jxFpsvJMfuMA0QUM) (Në të cilin jemi mbështetur gjatë realizimit të funksionit caesar)

3. [https://stackoverflow.com/a/5864174](https://stackoverflow.com/a/5864174) (Këtu jemi bazuar për ta realizuar komanden words tek funksioni count)

4. [https://www.novixys.com/blog/how-to-generate-rsa-keys-java/](https://www.novixys.com/blog/how-to-generate-rsa-keys-java/)

5. https://www.tutorialspoint.com/generate-random-bytes-in-java

6. https://gist.github.com/itarato/abef95871756970a9dad

7. https://mkyong.com/java/jce-encryption-data-encryption-standard-des-tutorial/

8. https://metacpan.org/pod/MIME::Base64

9. https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

10. https://www.tutorialspoint.com/java_cryptography/java_cryptography_creating_signature.htm

11. https://www.javatpoint.com/java-console-readpassword-method?fbclid=IwAR0YJ2Y6aRhGacGl4TaIgdV8YJAVFVmHahjp5ZdoZ1d-Ie65IBv6530SSTE
