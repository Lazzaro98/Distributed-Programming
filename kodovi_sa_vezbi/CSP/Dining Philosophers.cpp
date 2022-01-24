// dining philosophers

[Pfil (i:0..4) PFILOSOPHER || Fork(i:0..4)::FORK || Room::ROOM]

FORK:: [ left, right: integer; left = (i-1) % 5; right = i;
    *[
        Pfil(left)?in(); -> Pfil(left)?out();
        []
        Pfil(right)?in() -> Pfil(right)?out();
    ]
]

ROOM:: [ v:integer; v:=4;
    *[
        v > 0; (i:0..4) Pfil(i)? ticket() -> v := v - 1
        []
        (i:0..4) Pfil(i)?back()-> v := v + 1     
    ]
]

PFILOSOPHER:: [left,right: integer; left = i; right = (i+1)%5;
    *[
        true -> THINK;

        room!ticket();
        fork(right)!in();
        fork(left)!in();
        EAT;
        fork(left)!out();
        fork(right)!out();
        room!back()
    ]
]



// druga varijanta:


[(i:1..N)P(i):phil || coord: coord]

phil:: [
    *[
        true -> [
            THINK;
            coord!startEat();
            eat;
            coord!endEat();
        ]
    ]
]

coord :: [
    int [N] forkAvail = {2};
    *[
        forkAvail[i]==2; (i:1..N)P(i)?startEat() ->[
            forkAvail[(i+1)%N]++
            forkAvail[(i+N-1)%N]++;
        ]
        []
        (i:1..N)?P(i)? endEat() -> [
            forkAvail[(i+1)%N]++
            forkAvail[(i+N-1)%N]++;
        ]
    ]
]