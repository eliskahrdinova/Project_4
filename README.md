# Evidence květin (Plant Management)

Jednoduchá Java konzolová aplikace pro evidenci květin a jejich zálivky.  

Aplikace umožňuje načíst seznam květin ze souboru `kvetiny.txt`, zobrazit informace o zálivce, přidávat nové květiny, mazat je, řadit podle jména nebo data poslední zálivky a zjišťovat, které květiny potřebují zalít. Umožňuje také uložit seznam zpět do souboru a opětovně ho načíst.

Soubor `kvetiny.txt` používá **tabulátory (`\t`)** jako oddělovače a má tento formát:  
`jméno	TAB	poznámky	TAB	datum_zasazení(YYYY-MM-DD)	TAB	datum_poslední_zálivky(YYYY-MM-DD)	TAB	frekvence_zálivky`

Při načítání se kontroluje, že frekvence zálivky je větší než 0 a že datum poslední zálivky není dříve než datum zasazení.  
V případě chyby je vyhozena vlastní výjimka `PlantException`.

Projekt obsahuje třídy:
- `Plant` – reprezentuje jednu květinu  
- `PlantManagement` – správa seznamu květin  
- `PlantException` – ošetření chyb  
- `Main` – hlavní metoda, která vše spouští  
