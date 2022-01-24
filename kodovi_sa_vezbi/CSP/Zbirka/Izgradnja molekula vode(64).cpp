[Hydrogen(h: 1..nh) :: HYDROGEN || Oxygen(h: 1..no) :: OXYGEN || Barrier :: BARRIER]

HYDROGEN ::[
    id1, id2, id3 :integer;
    Barrier!BarrierExit();
    Barrier?ExitOk()(id1, id2, id3);
    BOND;
    Barrier!Confirm();
]

OXYGEN ::[
    id1, id2, id3 :integer;
    Barrier!BarrierExit();
    Barrier?ExitOk()(id1, id2, id3);
    BOND;
    Barrier!Confirm();
]

BARRIER :: [
    id1, id2, id3: integer;
    *[
        Hydrogen(id1: 1..nh)?Exit();
        Hydrogen(id2: 1..nh)?Exit();
        Oxygen(id3: 1..no)?Exit();

        Hydrogen(id1)!ExitOk(id1, id2, id3);
        Hydrogen(id2)!ExitOk(id1, id2, id3);
        Hydrogen(id3)!ExitOk(id1, id2, id3);

        Hydrogen(id1)?Confirm();
        Hydrogen(id2)?Confirm();
        Oxygen(id3)?Confirm()
    ]
    STOP;
]


//moze i uprosceno:


HYDROGEN :: [
    id1, id2, id3 : integer;
    *[    
        Barrier!Exit();
        Barrier?OkToExit(id1, id2, id3);
    ]
]

OXYGEN :: [
    id1, id2, id3 : integer;
    *[    
        Barrier!Exit();
        Barrier?OkToExit(id1, id2, id3);
    ]
]

BARRIER :: [
    id1, id2, id3: integer;
    *[
        Hydrogen(id1:1..nh)?Exit();
        Hydrogen(id2:1..nh)?Exit();
        Oxygen(id3:1..no)?Exit();

        Hydrogen(id1)!OkToExit();
        Hydrogen(id2)!OkToExit();
        Oxygen(id3)!OkToExit();
    ]
]