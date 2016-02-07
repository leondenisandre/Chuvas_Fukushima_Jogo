/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto.poo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Leon
 */
public class Game extends JFrame{
    Timer timer;
    private final int velocBola = 700;
    private final int capacB = 8;
    private final int numGot = 2;
    private final int gotasL = 80;
    private final int proporcao = 50;
    private final int numeroDeFases = 13;
    
    
    Parametro parametro = new Parametro(velocBola,capacB,numGot,gotasL,proporcao);

    private ArrayList<Desenho> bola = new ArrayList<>(); // 
    private ArrayList<Desenho> forma = new ArrayList<>();// 
    private ArrayList<Desenho> piso = new ArrayList<>();//
    private ArrayList<Desenho> nivel = new ArrayList<>();//
    private ArrayList<Desenho> planta = new ArrayList<>();//
    private ArrayList<Placar> placares = new ArrayList<>();
               
    private final int velocidadePersonagem = 6;
    private static int contadorVermelha = 0;
    private int contNivel = 1;
    private final int xPlanta = 5;
    private int yPlanta = 470; 
    private int larguraRight;
    private int larguraLeft;
    private int fase = 1;
    private int contaPontos = 0;   
    private int contSecador = 5;
    private int numeroDeGotas = 0;
    private int contaGotasCaidas = 0;
    private boolean jogoIniciado = true;
    private int contBlueBalde = 0;
    private boolean plantaMovDentro = true;
            
    private JButton bInicio = new JButton("Iniciar");
    private JPanel panelScores = new JPanel();
    private JLabel labelP = new JLabel(" ");
    private JLabel labelFase = new JLabel(" ");
    private JLabel labelGotasL = new JLabel(" ");
    private JLabel labelSecador = new JLabel(" ");
    private JPanel plogo = new JPanel();
    private JLabel labelLogo ;
    Container raiz = getContentPane();
    Painel pJogo = new Painel();
  
    public class Painel extends JPanel{
        Color balde = new Color(160,82,45);
        Color sienna = new Color(47, 79 ,79);
        Color fundo = new Color(150 ,205, 205);
        Color corB = new Color(250,128,114);
        Color cam = new Color(248, 248 ,255);
        Color berm = new Color(16,78,139);
        BufferedImage imagem;
        public Painel(){
          setFocusable(true);                  
            try{
                imagem = ImageIO.read(new File("F:/JAVA/Projeto POO/imagens/fundo.jpg"));
                JLabel label = new JLabel (new ImageIcon(imagem));
            }catch(IOException ex){
                ex.printStackTrace();
            }
            
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(fundo);
            g.drawImage(imagem, 0, 0, 450, 500, rootPane);
            //bolas
            for (int i = 0; i < bola.size(); i++) {
                Desenho y = bola.get(i);
                double cor = y.getCor();
                if(cor < 0.5){
                    g.setColor(Color.blue);
                }
                else{
                    g.setColor(Color.red);
                 }
                g.fillOval(y.getPosicaoX(),y.getPosicaoY(), y.getLargura() - 3, y.getAltura() - 3);
                g.drawOval(y.getPosicaoX(),y.getPosicaoY(), y.getLargura(), y.getAltura());
               
            }
            //personagem
            for (int i = 0; i < forma.size(); i++) {
               Desenho aux = forma.get(i);
               g.setColor(corB);
               if(aux.getCor() == 1){
                   g.setColor(Color.black);
               }
               else if(aux.getCor() == 2){
                   g.setColor(balde);
               }
               else if(aux.getCor() == 3){
                   g.setColor(cam);
               }
               else if(aux.getCor() == 4){
                   g.setColor(berm);
               }
               if(i == 0){
                   g.fillOval(aux.getPosicaoX(), aux.getPosicaoY(), aux.getLargura(), aux.getAltura());
               }
               else{
                    g.fillRect(aux.getPosicaoX(), aux.getPosicaoY(), aux.getLargura(), aux.getAltura());
               }                      
            }
            //piso
            for (int i = 0; i < piso.size(); i++) {
                Desenho p = piso.get(i);
                g.setColor(sienna);
                if(p.getPosicaoY()>378){
                    g.setColor(new Color(205 ,129 ,98));
                }
                g.fillRect(p.getPosicaoX(), p.getPosicaoY(), p.getLargura(), p.getAltura());
            }
            //nivel balde
            for(int i=0;i<nivel.size();i++){
                Desenho aux = nivel.get(i);
                Integer a1 = aux.getPosicaoX();
                Integer a2 = aux.getPosicaoY();
                if(contadorVermelha != 0){
                    g.setColor(Color.orange);
                    g.fillRect(a1,a2, aux.getLargura(), aux.getAltura());
                }
                else{
                    g.setColor(new Color(0, 191, 255));
                    g.fillRect(a1,a2, aux.getLargura(), aux.getAltura());
                }
            }
            //desenhando plantas
            for(int i=0;i<planta.size();i++){
                Desenho p = planta.get(i);
                g.setColor(new Color(0,139,69));
                g.fillOval(p.getPosicaoX(), p.getPosicaoY(), p.getLargura(), p.getAltura());
            } 
            
        }    
    }
        
    public void posicaoBolaInicial(){        
        int posicaoX = 6 + (int)(Math.random() * 380);
        double aux;
        Double cor = Math.random() + (int)(parametro.getProporcao()/100);
        Desenho ball = new Desenho(posicaoX, 0 ,6,6, cor);
        bola.add(ball);     
    }
    
    public void novoPosicaoBola(){       
       for (int i = 0; i < bola.size(); i++) {
           Desenho ball = bola.get(i);
           int v2 = ball.getPosicaoY();
           ball.setPosicaoY(v2+5);
        }
    }
    public void adicionaPersonagem(){
        int valorX = 40, valorY = 325;
        //balde
        Desenho f17 = new Desenho(valorX, valorY, 6,6,2);
        Desenho f18 = new Desenho(valorX + 6, valorY, 6,6,2);
        Desenho f19 = new Desenho(valorX + 12, valorY, 6,6,2);
        Desenho f20 = new Desenho(valorX + 18, valorY, 6,6,2);
        Desenho f21 = new Desenho(valorX + 24, valorY, 6,6,2);
        Desenho f22 = new Desenho(valorX + 30, valorY, 6,6,2);
        Desenho f23 = new Desenho(valorX + 36, valorY, 6,6,2);
        Desenho f24 = new Desenho(valorX + 42, valorY, 6,6,2);
        
        Desenho f25 = new Desenho(valorX, valorY - 5, 6,1,2);//borda esq
       
        Desenho f26 = new Desenho(valorX + 42, valorY - 5, 6,1,2);//borda dire
              
        //cabeca
        Desenho f1 = new Desenho(valorX + 16, valorY + 5, 12,12,0);
        //corpo
        Desenho f2 = new Desenho(valorX + 20, valorY + 17, 8,6,3);
        Desenho f3 = new Desenho(valorX + 20, valorY + 23, 8,6,3);
        Desenho f4 = new Desenho(valorX + 20, valorY + 29, 8,6,4);
        //braco direito
        Desenho f5 = new Desenho(valorX, valorY + 5, 6,6,0);
        Desenho f6 = new Desenho(valorX +6, valorY + 10, 6,6,0);
        Desenho f7 = new Desenho(valorX +12, valorY + 15, 6,6,3);
        //braco esquerdo
        Desenho f8 = new Desenho(valorX + 42, valorY + 5, 6,6,0);
        Desenho f9 = new Desenho(valorX + 36, valorY + 10, 6,6,0);
        Desenho f10 = new Desenho(valorX + 30, valorY + 15, 6,6,3);
        //perna direita
        Desenho f11 = new Desenho(valorX + 14, valorY + 35, 6,6,4);
        Desenho f12 = new Desenho(valorX + 8, valorY + 41, 6,6,0);
        Desenho f13 = new Desenho(valorX + 2, valorY + 47, 6,6,1);
        //perna esquerda
        Desenho f14 = new Desenho(valorX + 28, valorY + 35, 6,6,4);
        Desenho f15 = new Desenho(valorX + 34, valorY + 41, 6,6,0);
        Desenho f16 = new Desenho(valorX + 40, valorY + 47, 6,6,1);
        
        //boneco
        forma.add(f1);forma.add(f2);forma.add(f3);forma.add(f4);
        forma.add(f5);forma.add(f6);forma.add(f7);forma.add(f8);
        forma.add(f9);forma.add(f10);forma.add(f11);forma.add(f12);
        forma.add(f13);forma.add(f14);forma.add(f15);forma.add(f16);
        
        //balde
        forma.add(f17);forma.add(f18);
        forma.add(f19);forma.add(f20);
        forma.add(f21);forma.add(f22);
        forma.add(f23);forma.add(f24);
        forma.add(f25);forma.add(f26);
        
        //aumenta cap balde
        int aux = (int) parametro.getCapacidadeBalde();
        int esqX = 40; int coodY = 324;//valores da borda esq
        int dirX = 82; //int dirY = 324;//valores da borda dir
        for (int i = 0; i < aux; i++) {
            Desenho novaEsq = new Desenho(esqX,coodY,6,1,2);//adiciona na esq
            Desenho novaDir = new Desenho(dirX,coodY,6,1,2);//adiciona na dir
            forma.add(novaEsq);
            forma.add(novaDir);
            coodY--;
        }
        
        //piso
        int y = 378;
        for (int i = 0; i < 9; i++) {
            int codx = 0;
            for (int j = 0; j < 45; j++) {
               Desenho p0 = new Desenho(codx,y,10,10,0);
               piso.add(p0);
               codx+=10;
            }
            y+=10;
        }
        //adicionando plantas
        int plantx = 10;
        for (int i = 0; i < 6; i++) {                       
            Desenho plant1 = new Desenho(plantx, 460,10,10,0);//left down
            Desenho plant2 = new Desenho(plantx + 20 ,460,10,10,0); //right down
           
            Desenho plant3 = new Desenho(plantx - 10,455,10,10,0);//left up
            Desenho plant4 = new Desenho(plantx + 30,455,10,10,0); //right up
             
            planta.add(plant1);//0
            planta.add(plant2);//impar
            planta.add(plant3);//par
            planta.add(plant4);//impar
            
            plantx = plantx + 70;
        }
        
    }
    
    public void plantaCresce(){
        if(!planta.isEmpty()){
            int aux = 20;
            for (int i = 0; i < 6; i++) {
                Desenho plan = new Desenho(aux,yPlanta,10,5,0);
                planta.add(plan);
                aux = aux + 70;
            }
            //mov
            for (int i = 0; i < planta.size(); i++) {
                Desenho p = planta.get(i);
                int y = p.getPosicaoY();
                p.setPosicaoY(y-1);
            }
        }
    }
    
    //animacao planta
    public void movimentaPlanta(){
        if(plantaMovDentro){
            for (int i = 0; i < planta.size(); i++) {
                Desenho plant = planta.get(i);
                int aux = plant.getPosicaoX();
                if(i<24){
                    if((i==0) ||(i%2 == 0)){
                       plant.setPosicaoX(aux+15);
                    }
                    else{
                       plant.setPosicaoX(aux-15);
                    }
                }
                
            }
            plantaMovDentro= false;
        }
        else{
           for (int i = 0; i < planta.size(); i++) {
                Desenho plant = planta.get(i);
                int aux = plant.getPosicaoX();
                if(i<24){
                    if((i==0) ||(i%2 == 0)){
                       plant.setPosicaoX(aux-15);
                    }
                    else{
                       plant.setPosicaoX(aux+15);
                    }
                }
            }
            plantaMovDentro = true;
        }
    }
    //elimina personagem
    public void delPerso(){
       addPlacar();
       restaura();
       limpa();
       contaPontos = 0;
       fase = 1;
       raiz.remove(pJogo);
       raiz.add(plogo);
       repaint();
       bInicio.setText("Iniciar");
       bInicio.setBackground(new Color(82,139,139));
       panelScores.setVisible(false);
       salvar();
    }
    
    //restaura
    public void restaura(){
        parametro.setVelocidadeQueda(velocBola);
        parametro.setCapacidadeBalde(capacB);
        parametro.setnGotasCiclo(numGot);
        parametro.setnGotasLetais(gotasL);
        timer.setDelay(velocBola);
        forma.clear();//recolocar o o personagem padrao
        jogoIniciado = true;
    }
    
    //zera valores
    public void limpa(){
        bola.clear();
        planta.clear();
        yPlanta = 470;
        nivel.clear();
        contSecador = 5;
        contaGotasCaidas = 0;
    }
    //faz alteraçoes nos parametros quando passa de fase
    public void funcF(){
        float aux = parametro.getCapacidadeBalde();
        float aux2 = parametro.getVelocidadeQueda();
        float aux3 = parametro.getnGotasCiclo();
        float aux4 = parametro.getnGotasLetais();
        
        float v1 =  (float) (aux + (aux*0.20)); //
        float v2 =  (float) (aux2 - (aux2*0.20));//
        float v3 =  (float) (aux3 + (aux3*0.10));//
        float v4 =  (float) (aux4 - (aux4*0.10));//
        
        parametro.setCapacidadeBalde(v1);
        parametro.setVelocidadeQueda(v2);      
        parametro.setnGotasCiclo(v3);
        parametro.setnGotasLetais(v4);
        
        System.out.println("Cap. Balde: "+ v1);
        System.out.println("Velc. Bola: "+ v2);
        System.out.println("Num. Gotas Ciclo: "+ v3);
        System.out.println("Num. Gotas Letal: "+ v4);
        
    }
    //fase
    public void chamaFase(){
        forma.clear();
        jogoIniciado = true;
        if(fase <= numeroDeFases){
           JOptionPane.showMessageDialog(pJogo, "Parabéns, Você passou para o Nível "+fase);
           funcF();
           timer.setDelay((int) parametro.getVelocidadeQueda());
           timer.start();
        }
        else{
            JOptionPane.showMessageDialog(pJogo,"Você Zerou o Jogo");
            restaura();
            limpa();
            delPerso();
        }
    }

    //ordena placar
    public void ordenaPlacar(){
        for (int i = 0; i < placares.size() - 2; i++) {
            for (int j = 1; j < placares.size() ; j++) {
                Placar  ant = placares.get(i);
                Placar  prox = placares.get(j);
                
                int x = ant.getPonto(); 
                String x1 = ant.getName();
                
                int y = prox.getPonto(); 
                String y1 = prox.getName();
                
                if(y > x){
                   ant.setPonto(y); ant.setName(y1);
                   prox.setPonto(x); prox.setName(x1);
                }
            }
        }
    }
    
    //adicionando placar
    public void addPlacar(){
        int aux;
        String nome;
        if(placares.size() < 10){
            aux = JOptionPane.showConfirmDialog(pJogo,"Gostaria de Salvar sua pontuação?", null, JOptionPane.YES_NO_OPTION);
            if(aux == JOptionPane.OK_OPTION){
                nome = JOptionPane.showInputDialog(pJogo,"Digite seu nome: ");
                if(placares.isEmpty()){
                   placares.add(new Placar(nome,contaPontos));
                }
                else{
                   placares.add(new Placar(nome,contaPontos));
                }
            }
            ordenaPlacar();
        }
        else{
            aux = JOptionPane.showConfirmDialog(pJogo,"Gostaria de Salvar sua pontuação?", null, JOptionPane.YES_NO_OPTION);
            int i = placares.size() - 1; 
            Placar p = placares.get(i);
            if(contaPontos > p.getPonto()){
                if(aux == JOptionPane.OK_OPTION){
                    nome = JOptionPane.showInputDialog(pJogo,"Digite seu nome: ");
                    placares.remove(p);
                    placares.add(i,new Placar(nome,contaPontos));
                }           
            }
            else {
                if(aux == JOptionPane.OK_OPTION){
                    new Placares(placares);      
                }    
            }   
            ordenaPlacar();
        }
        
    }
    //movimentação
    public void movimentoRigth(){
        for (int i = 0; i < forma.size(); i++) {
            Desenho aux = forma.get(i);
            int a = aux.getPosicaoX();
            aux.setPosicaoX(a + velocidadePersonagem);
        }
        for (int i = 0; i < nivel.size(); i++) {
            Desenho aux = nivel.get(i);
            int a = aux.getPosicaoX();
            aux.setPosicaoX(a + velocidadePersonagem);
        }   
    }
    public void movimentoLeft(){
        for (int i = 0; i < forma.size(); i++) {
            Desenho aux = forma.get(i);
            int a = aux.getPosicaoX();
            aux.setPosicaoX(a - velocidadePersonagem);
        } 
        for (int i = 0; i < nivel.size(); i++) {
            Desenho aux = nivel.get(i);
            int a = aux.getPosicaoX();
            aux.setPosicaoX(a - velocidadePersonagem);
        }
    }
    
    public void adicionaNivelPlanta(){
        int x,y,x1,x4,y2,y4;
       
        if(!forma.isEmpty()){
            x1 = forma.get(16).getPosicaoX(); //prime Esq
            x4 = forma.get(23).getPosicaoX(); //ult dir
            y2 = forma.get(forma.size()-1).getPosicaoY();//ult add
            y4 = forma.get(23).getPosicaoY(); //ult dir       

            if(nivel.isEmpty()){
              contNivel = 1;
              contadorVermelha = 0;
            }
            for (int i = 0; i< bola.size(); i++) {
                Desenho b = bola.get(i);
                x = b.getPosicaoX();
                y = b.getPosicaoY();
                Double cor = b.getCor();
                if(contNivel <= (int) parametro.getCapacidadeBalde()){
                    //area do balde
                    if((x >= x1 && x <= x4) && (y >= y2 && y<= y4 ) ){
                        //adicionando niveis
                        bola.remove(i);
                        Desenho novoN = new Desenho(x1+5, y4 - contNivel, 38, 1,cor);
                        nivel.add(novoN);
                        if(cor >= 0.5 && contadorVermelha < 2){
                            contadorVermelha++; 
                        }
                        else if(cor < 0.5){
                            contaPontos = contaPontos + 5;
                            contBlueBalde++;
                        }
                        else if(cor >= 0.5){
                            contaPontos = contaPontos - 10;
                        }
                        contNivel++;
                    }
                }
                verificGotasCaidas();
                //plantas
                if(((x >=0 && x <= 370) && (y >= 370)) && (cor<0.5)){   
                    bola.remove(i);//remove bola
                    plantaCresce();
                    yPlanta++;
                    contaPontos = contaPontos - 3;
                    contaGotasCaidas++;
                } 
                labelGotasL.setText(""+contaGotasCaidas);
                labelP.setText(""+contaPontos); 
            }
            if(contadorVermelha == 2){
                for (int i = 0; i < nivel.size(); i++) {
                    Desenho f = nivel.get(i);
                    nivel.remove(i);
                }
                for (int i = 0; i < contBlueBalde; i++) {
                    plantaCresce();
                    yPlanta++;
                }
                contBlueBalde = 0;
            }
        }
    }  
    
    public void verificGotasCaidas(){
    //verifica gotas azuis caidas é maxima
        if(contaGotasCaidas == (int) parametro.getnGotasLetais()){
            for (int i = 0; i < forma.get(0).getPosicaoY(); i++) {
                plantaCresce();
                yPlanta++;
            }
            repaint();
            timer.stop();
            JOptionPane.showMessageDialog(pJogo,"Game Over");
            delPerso();
        }
    }
    
    public void logotipo(){
        BufferedImage imagem;
        try{
            imagem = ImageIO.read(new File("F:/JAVA/Projeto POO/imagens/logo.png"));
            labelLogo = new JLabel (new ImageIcon(imagem));
            plogo.add(labelLogo);
            raiz.add(plogo,BorderLayout.CENTER);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void verificaBalde(){
    //verifica balde
        if(nivel.size() == (int) parametro.getCapacidadeBalde()){
            fase++;
            timer.stop();
            limpa();
            chamaFase();
            System.out.println(""+plantaMovDentro);
            plantaMovDentro = true;
        } 
    }
 
    public Game(){
        logotipo();
        recuperar();
        bInicio.setFocusable(false);
        bInicio.setForeground(Color.white);
        bInicio.setBackground(new Color(82,139,139));
        timer = new Timer(velocBola, new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                raiz.remove(plogo);
                raiz.add(pJogo);
                raiz.add(panelScores, BorderLayout.NORTH);
                bInicio.setText("Pausar");
                bInicio.setBackground(new Color(85, 26, 139));
                labelSecador.setText(""+contSecador);
                labelFase.setText(""+fase);
                panelScores.setVisible(true);
                
                    numeroDeGotas = 1 + (int)(Math.random() * (int) parametro.getnGotasCiclo()); 
                    if(jogoIniciado){
                        adicionaPersonagem();
                        jogoIniciado = false;
                        plantaMovDentro = true;
                    }

                    for(int j = 0; j< numeroDeGotas;j++){
                        posicaoBolaInicial();
                    }
                    novoPosicaoBola();
                    adicionaNivelPlanta(); 
                    movimentaPlanta();
                    verificaBalde();
                    pJogo.grabFocus();
                    pJogo.repaint();
                    salvar();
            }          
        });
        
        timer.setRepeats(true);
        bInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(timer.isRunning()){
                    timer.stop();
                    bInicio.setText("Retomar");
                    bInicio.setBackground(Color.RED);
                }
                else{
                    timer.start();                
                }
            }
        });       
        pJogo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                larguraRight = forma.get(25).getPosicaoX();
                larguraLeft = forma.get(24).getPosicaoX();
                if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (timer.isRunning()) && (larguraRight < 390)) {             
                    movimentoRigth();
                    //pJogo.grabFocus();
                    repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT && timer.isRunning() && (larguraLeft > 4)){
                    movimentoLeft();
                    //pJogo.grabFocus();
                    repaint();
                }               
                else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_P){                   
                    if(timer.isRunning()){
                        timer.stop();
                        bInicio.setText("Retomar");
                        bInicio.setBackground(Color.RED);
                    }
                }
            }
        });
        
        //secador
        pJogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 1 && timer.isRunning()){
                    int x = e.getX(); 
                    int y = e.getY();
                    for (int i = 0; i < bola.size(); i++) {
                        Desenho bola1 = bola.get(i);
                        int xBola = bola1.getPosicaoX();
                        int yBola = bola1.getPosicaoY();
                        if( ((x >= xBola - 7 && x <= xBola + 7 )&& (y >= yBola - 7 && y <= yBola + 7)) && contSecador > 0){
                            bola.remove(i);
                            pJogo.grabFocus();
                            pJogo.repaint();
                            contSecador --;
                        }
                    }
                }      
            }
        });     
        panelScores.setLayout(new BoxLayout(panelScores, BoxLayout.X_AXIS));
        panelScores.add(Box.createHorizontalStrut(5));
        panelScores.add(new JLabel("Fase: "));
        panelScores.add(labelFase);
        panelScores.add(Box.createHorizontalStrut(10));
        panelScores.add(new JLabel("Pontos: "));
        panelScores.add(labelP);
        panelScores.add(Box.createHorizontalStrut(10));
        panelScores.add(new JLabel("Secador: "));
        panelScores.add(labelSecador);
        panelScores.add(Box.createHorizontalGlue());
        panelScores.add(new JLabel("Gotas Letais:  "));
        panelScores.add(labelGotasL);
        panelScores.add(Box.createHorizontalStrut(15));
        raiz.add(bInicio, BorderLayout.SOUTH);
        labelGotasL.setForeground(Color.red);
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu menuArquivo = new JMenu("Arquivo");
        menubar.add(menuArquivo);
        
        JMenuItem itemPlacares = new JMenuItem("Placares");
        menuArquivo.add(itemPlacares);
        JMenuItem itemReset = new JMenuItem("Reiniciar Jogo");
        menuArquivo.add(itemReset);
        JMenuItem itemParametros = new JMenuItem("Parâmetro");
        menuArquivo.add(itemParametros);
        JMenuItem itemSair = new JMenuItem("Sair");
        menuArquivo.add(itemSair);
        
        //placares
        itemPlacares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Placares(placares);
            }
        });
        //reiniciar o jogo
        itemReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                addPlacar();
                restaura();
                limpa();
                contaPontos = 0;
                fase = 1;
                timer.start();
            }
        });
        //parametros
        itemParametros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               limpa();
               forma.clear();
               jogoIniciado = true;
               FrameParametro param = new FrameParametro(parametro,timer); 
            }
        });
        //sair
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Color fundo = new Color(210,180,140);
        panelScores.setBackground(fundo);
        setTitle("Jogo");
        setSize(420,570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    } 
    
    //salvar 
    public void salvar () {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter ("backupPontos.txt"));
            for (int i = 0; i < placares.size(); i++) {
                Placar p = placares.get(i);
                p.salvar(writer);
                writer.newLine();                
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void recuperar () {
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("backupPontos.txt"));
            String linha = null;
            while ((linha = reader.readLine()) != null) {
                String campos [] = linha.split(";");
                if(campos[0].equals("pontos")){
                    Placar placar = new Placar(campos[1],Integer.parseInt(campos[2]));
                    placares.add(placar);
                }
            }
         reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace ();
        } catch (IOException ex) {
            ex.printStackTrace ();
        }
    }
    
    public static void main(String[] args) {
        new Game();
    }
}
