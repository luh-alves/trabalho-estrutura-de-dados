package compactadordearquivo;

/**
 *
 * @author Luciana Alves
 */
public class CompactadorDeArquivo {

    private static String lerDoArquivo() {
        return "Dear Sally, Please, please do it--it would please Mary very, very much. And Mary would do everything in Mary's power to make it pay off for you. -- Thank you very much --";
    }

    private static void escreverNoArquivo(String saida) {
        System.out.println(saida);
    }

    private static String[] dividirEmPalavras(String texto) {
        String separadoraDePalavras = "special" + System.currentTimeMillis() + "divider";
        String resultado = "";
        String palavraAtual = "";
        for (int i = 0; i < texto.length(); i++) {
            char caracterAtual = texto.charAt(i);
            boolean ehLetraOuNumero = Character.isLetter(caracterAtual) || Character.isDigit(caracterAtual);
            if (ehLetraOuNumero) {
                palavraAtual += caracterAtual;
            } else {
                if (palavraAtual.length() > 0) {
                    resultado += palavraAtual;
                    resultado += separadoraDePalavras;
                    palavraAtual = "";
                }

                resultado += caracterAtual;
                if (i < texto.length() - 1) {
                    resultado += separadoraDePalavras;
                }
            }
        }

        return resultado.split(separadoraDePalavras);
    }

    public static String compactarTexto(String texto) {
        String textoCompactado = "";
        String palavras[] = dividirEmPalavras(texto);
        int i;
        Lista lista = new Lista();
        for (i = 0; i < palavras.length; i++) {
            String palavraAtual = palavras[i];

            if (!Character.isLetter(palavraAtual.charAt(0))) {
                textoCompactado = textoCompactado + palavraAtual;

            } else {
                int posicao = lista.buscarElemento(palavraAtual);
                if (posicao == -1) {
                    lista.insereInicio(palavraAtual);
                    textoCompactado = textoCompactado + palavraAtual;
                } else {
                    textoCompactado = textoCompactado + posicao;

                    lista.removerElemento(palavraAtual);
                    lista.insereInicio(palavraAtual);
                }
            }

        }
        return textoCompactado;
    }

    public static String descompactarTexto(String texto) {
        String textoDescompactado = "";
        String palavras[] = dividirEmPalavras(texto);
        int i;
        Lista lista = new Lista();
        for (i = 0; i < palavras.length; i++) {
            String palavraAtual = palavras[i];

            boolean ePalavra = Character.isLetter(palavraAtual.charAt(0));
            boolean eNumero = Character.isDigit(palavraAtual.charAt(0));

            if (! ePalavra && ! eNumero) {
                textoDescompactado = textoDescompactado + palavraAtual;

            } else {
                if (eNumero) {
                    int posicao = Integer.parseInt(palavraAtual);
                    String palavra = lista.buscarElementoPorPosicao(posicao);
                    textoDescompactado = textoDescompactado + palavra;
                    lista.removerElemento(palavra);
                    lista.insereInicio(palavra);

                } else {
                    lista.insereInicio(palavraAtual);
                    textoDescompactado = textoDescompactado + palavraAtual;
                }
            }

        }
        return textoDescompactado;
    }

    public static void main(String[] args) {
        String entrada = lerDoArquivo();
        String compactado = compactarTexto(entrada);
        String descompactado = descompactarTexto(compactado);
        System.out.println("----> T E X T O   C O M P A C T A D O <----");
        escreverNoArquivo(compactado);

        System.out.println("----> T E X T O   D E S C O M P A C T A D O <----");
        escreverNoArquivo(descompactado);

    }

}
