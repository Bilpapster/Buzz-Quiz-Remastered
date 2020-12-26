package GUI;

public enum KeyboardSet {
    _Q_W_A_S, _O_P_K_L, _A_B_C_D, _1_2_3_4, _A_S_Z_X, _K_L_N_M;

    public String getPureSeriesOfCharacters() {
        return super.toString().replaceAll("_", "").toLowerCase();
    }
}
