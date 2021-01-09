package GUI;

/**
 * A simple enumeration that represents a keyboard answering set. The set contains the keyboard characters that are
 * accepted for the players in order to declare their choice among the available in each question.
 *
 * @author Vasileios Papastergios
 */
public enum KeyboardSet {
    _Q_W_A_S, _O_P_K_L, _A_B_C_D, _1_2_3_4, _A_S_Z_X, _K_L_N_M;

    public String getPureSeriesOfCharacters() {
        return super.toString().replaceAll("_", "").toLowerCase();
    }
}
