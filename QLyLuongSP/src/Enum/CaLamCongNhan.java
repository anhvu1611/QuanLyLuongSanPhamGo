
package Enum;

/**
 *
 * @author ACER
 */
public enum CaLamCongNhan {
    CAMOT("Ca Một"),

    CAHAI("Ca Hai"),
    CABA("Ca Ba");
    private final String caLam;

    private CaLamCongNhan(String caLam) {
        this.caLam = caLam;
    }

    public String getCaLam() {
        return caLam;
    } 
    

    public String toString() {
    	return getCaLam();
    }
}

