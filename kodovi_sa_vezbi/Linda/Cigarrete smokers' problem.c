void agent() {
    int n;
    while(1) {
        n = rand() % 3; // random broj od 0 do 2
        switch(n){
            case 0:
                out("Praper");
                out("Tobacco");
                break;
            case 1:
                out("Tobacco");
                out("Matches");
                break;
            case 2:
                out("Matches");
                out("Paper");
                break;
        }
        out("Watch");
        in("OK");
    }
}

void smoker_with_matches(){
    while(1){
        in("Watch");
        if(rdp("Paper" && rdp("Tobacco"))){
            in("Paper");
            in("Tobacco");
            enjoy();

            in("num blocked", ?n);
            for(int i=0;i<n;i++)
                out("next");
            
            for(int i=0;i<n;i++)
                in("nextOk");

            out("OK");
        }
        else {
            in("num blocked", ?n);
            out("num blocked", n+1);
            out("Watch");
            in("next");
            out("nextOk");
        } 
    }
}

initialize(){
    out("number blocked", 0);
    eval(agent());
    eval(smoker_with_matches());
    eval(smoker_with_paper());
    eval(smoker_with_tobacco);
    out("Match");
}