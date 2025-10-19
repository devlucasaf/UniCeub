// relogio.cpp
#include <bits/stdc++.h>
#ifdef _WIN32
#include <windows.h>
#endif

using namespace std;

struct Screen {
    int W, H;
    vector<string> buf;
    Screen(int w, int h): W(w), H(h), buf(h, string(w, ' ')) {}
    void clear(char c=' ') { for (auto &r: buf) fill(r.begin(), r.end(), c); }
    void plot(int x, int y, char c) {
        if (0 <= x && x < W && 0 <= y && y < H) buf[y][x] = c;
    }
    void drawLine(int x0,int y0,int x1,int y1,char c) { // Bresenham
        int dx=abs(x1-x0), dy=-abs(y1-y0);
        int sx = x0<x1?1:-1, sy = y0<y1?1:-1, err = dx+dy;
        while (true) {
            plot(x0,y0,c);
            if (x0==x1 && y0==y1) break;
            int e2=2*err;
            if (e2>=dy){ err+=dy; x0+=sx; }
            if (e2<=dx){ err+=dx; y0+=sy; }
        }
    }
    void flush() {
        // Move cursor to home and print buffer
        cout << "\x1b[H";
        for (auto &r: buf) cout << r << '\n';
        cout.flush();
    }
};

#ifdef _WIN32
void enableVT() {
    HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);
    if (hOut == INVALID_HANDLE_VALUE) return;
    DWORD dwMode = 0;
    if (!GetConsoleMode(hOut, &dwMode)) return;
    dwMode |= ENABLE_VIRTUAL_TERMINAL_PROCESSING;
    SetConsoleMode(hOut, dwMode);
}
#endif

int main() {
#ifdef _WIN32
    enableVT();
#endif
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    // Tamanho do “canvas”
    const int W = 61;   // ímpar para centralizar
    const int H = 31;
    Screen sc(W, H);

    // Preparar terminal: esconder cursor e limpar
    cout << "\x1b[?25l";           // hide cursor
    cout << "\x1b[2J\x1b[H";       // clear + home

    const int cx = W/2;
    const int cy = H/2;
    const int R  = min(W, H)/2 - 2;     // raio do relógio
    const int rHour = R*0.55;           // comprimento ponteiro hora
    const int rMin  = R*0.80;           // comprimento ponteiro minuto
    const int rSec  = R*0.90;           // comprimento ponteiro segundo

    // Pré-desenhar marcas do mostrador (12 horas + anel)
    vector<pair<int,int>> bezel;
    for (int deg = 0; deg < 360; deg+=2) {
        double th = deg * M_PI / 180.0;
        int x = (int)round(cx + R * cos(th));
        int y = (int)round(cy + R * sin(th));
        bezel.push_back({x,y});
    }
    vector<pair<int,int>> ticksBig, ticksSmall;
    for (int i=0;i<60;i++){
        double th = (i/60.0)*2*M_PI; // 0 rad = 3h, mas vamos reposicionar abaixo
        double base = th - M_PI/2;   // 0 -> 12h
        double r1 = (i%5==0) ? (R-0) : (R-1);
        double r2 = (i%5==0) ? (R-3) : (R-1);
        int x1 = (int)round(cx + r1 * cos(base));
        int y1 = (int)round(cy + r1 * sin(base));
        int x2 = (int)round(cx + r2 * cos(base));
        int y2 = (int)round(cy + r2 * sin(base));
        if (i%5==0) ticksBig.push_back({x1,y1}), ticksBig.push_back({x2,y2});
        else        ticksSmall.push_back({x1,y1});
    }

    auto nowAngles = [](){
        using namespace chrono;
        auto tp = system_clock::now();
        time_t tt = system_clock::to_time_t(tp);
        tm local{};
#ifdef _WIN32
        localtime_s(&local, &tt);
#else
        local = *std::localtime(&tt);
#endif
        // fração de segundo para suavizar
        auto secs = time_point_cast<milliseconds>(tp).time_since_epoch() % seconds(60);
        double sFrac = secs.count()/1000.0; // 0..59.999

        double sec = local.tm_sec + (sFrac - local.tm_sec >= 0 ? (sFrac - local.tm_sec) : 0);
        double min = local.tm_min + sec/60.0;
        double hr  = (local.tm_hour%12) + min/60.0;

        // Converter para ângulo com 0 em 12h e sentido horário
        auto toTheta = [](double units, double full){
            double theta = (units/full)*2*M_PI - M_PI/2; // 12h = -pi/2
            return theta;
        };
        return tuple<double,double,double>(
            toTheta(hr, 12.0),
            toTheta(min, 60.0),
            toTheta(sec, 60.0)
        );
    };

    // Loop principal
    using namespace chrono;
    auto nextFrame = steady_clock::now();
    const auto frameDt = 50ms; // ~20 FPS
    bool running = true;
#ifdef _WIN32
    // Deixa Ctrl+C funcionar rapidamente
    SetConsoleCtrlHandler(NULL, FALSE);
#endif
    while (running) {
        nextFrame += frameDt;
        sc.clear(' ');

        // Borda/anel
        for (auto [x,y]: bezel) sc.plot(x,y,'.');

        // Marcações (minutos e horas)
        for (size_t i=0;i<ticksSmall.size();++i) sc.plot(ticksSmall[i].first, ticksSmall[i].second, '.');
        for (size_t i=0;i<ticksBig.size(); i+=2) {
            sc.plot(ticksBig[i].first, ticksBig[i].second, 'o');
            sc.plot(ticksBig[i+1].first, ticksBig[i+1].second, 'o');
        }

        // Centro
        sc.plot(cx, cy, '•');

        // Ângulos atuais (horário → aumenta sentido horário)
        double thH, thM, thS;
        tie(thH, thM, thS) = nowAngles();

        // Pontas dos ponteiros
        int hx = (int)round(cx + rHour * cos(thH));
        int hy = (int)round(cy + rHour * sin(thH));
        int mx = (int)round(cx + rMin  * cos(thM));
        int my = (int)round(cy + rMin  * sin(thM));
        int sx = (int)round(cx + rSec  * cos(thS));
        int sy = (int)round(cy + rSec  * sin(thS));

        // Desenhar ponteiros (ordem: hora, minuto, segundo)
        sc.drawLine(cx, cy, hx, hy, '#');   // hora
        sc.drawLine(cx, cy, mx, my, '*');   // minuto
        sc.drawLine(cx, cy, sx, sy, '+');   // segundo

        // Janela digital (HH:MM:SS) abaixo
        auto tp = system_clock::now();
        time_t tt = system_clock::to_time_t(tp);
        tm local{};
#ifdef _WIN32
        localtime_s(&local, &tt);
#else
        local = *std::localtime(&tt);
#endif
        char timebuf[16];
        snprintf(timebuf, sizeof(timebuf), "%02d:%02d:%02d",
                local.tm_hour, local.tm_min, local.tm_sec);
        string label = string(" Relogio ASCII | ") + timebuf + " ";
        int lx = max(0, cx - (int)label.size()/2);
        int ly = min(H-1, cy + R + 1);
        for (int i=0;i<(int)label.size() && lx+i<W;i++) sc.plot(lx+i, ly, label[i]);

        sc.flush();

        // Dormir até próximo frame
        this_thread::sleep_until(nextFrame);
        // Para sair, use Ctrl+C no terminal
    }

    // Mostrar cursor de volta (normalmente não chegamos aqui)
    cout << "\x1b[?25h";
    return 0;
}
