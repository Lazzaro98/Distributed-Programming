// primer pipeline-a 

[nulti :: SIEVE(0) || sredina :: SIEVE(i:1..99) || poslednji :: SIEVE(100)]

SIEVE(0) :: [
    print!2; // dvojka ce biti odmah ispisana jer je prime
    n:integer; n := 3; // sledeci prime number je 3
    b:boolean; b := true;
    *[
        b -> SIEVE(1)!n; n := n + 2; // tako da daljim procesima filtriramo sve parne brojeve, koji sigurno nisu prime. i to tako sto krenemo od prvog neparnog nakon 2, sto je 3, i saljemo brojeve za 2 vece konstantno
        []
        b; SIEVE(100)?done() -> b := false;//medjutim ako od 100. procesa dobijemo done(), onda ne saljemo vise, i setujemo b na false, znaci da je program zavrsio sta je trebalo
    ]
]

// 

SIEVE(i:1..99) :: [ // svi procesi u sredini sada samo prosledjuju ono sto dobiju od prethodnika, s tim sto filtriraju dobijene vrednposti
    prime, mprime:integer; // mprime- multiply prime, ce biti umnozak od prime broja. Zasto nam treba, pa zato sto filtriramo uvek umnoske, jer ako postoji umnozak, njega treba izbaciti, znaci da nije prime number
    SIEVE(i-1)?prime; // prvo primimo prvi broj koji nam salje prethodnik. Taj broj je sigurno prime. Ispisemo ga odmah, i setujemo mprime na prime.
    print!prime;
    mprime := prime;
    *[
        m:integer; SIEVE(i-1)?m -> // nakon sto smo primili prvi broj, sada od prethodnika konstantno primamo sledece brojeve
            *[m > mprime ->mprime := mprime + prime;] //kad god primimo broj m od prethodnika, mprime povecamo da bude ili > ili = m. Ako je =, nasli smo umnozak. Ako je >, to nije umnozak.
            [
                m==mprime -> skip; // jeste umnozak, ne saljemo ga dalje. Filtrirali smo ga. U suprotnom, samo ga saljemo sledecem procesu
                [] 
                m<mprime -> SIEVE(i+1)!m  
            ]
    ]
]


//poslednji proces treba samo da ispise ono sto je dobio od prethodnika i da posalje nultom procesu da je kraj
SIEVE(100) :: [ 
    n:integer;
    SIEVE(99)?n -> print!n; SIEVE(0)!done()
    *[SIEVE(99)?n -> skip] // ukoliko dobije jos nesto od prethodnika, sve sto dobije smao ignorise
]