void signal(String sem){
    out(sem);
}

void wait(String sem){
    in(sem);
}

void init(String sem, unsigned val){
    for(int i = 0;i<val;i++)
        out(sem);
}