package ar.com.ada.api.pootflix.entities;

public class Pais {
    public enum PaisEnum {
        ARGENTINA(32), ESTADOS_UNIDOS(840), VENEZUELA(862), ESPAÑA(724), AUSTRALIA(036), ISRAEL(376), REINO_UNIDO(44),
        FRANCIA(250), NUEVA_ZELANDA(554), CHINA(156), CANADA(124);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private PaisEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static PaisEnum parse(Integer id) {
            PaisEnum status = null; // Default
            for (PaisEnum item : PaisEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

}