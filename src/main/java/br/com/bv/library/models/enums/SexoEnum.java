package br.com.bv.library.models.enums;

public enum SexoEnum {
    MASCULINO("Masculino", "M"),
    FEMININO("Feminino", "F");  
    
    private String displayValue;
    private String value;
    
    private SexoEnum(String displayValue, String value) {
        this.displayValue = displayValue;
        this.value = value;     
    }
        
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.displayValue;
    }
    
    public static SexoEnum getSexo(String value) {
        
        if (value.equals(SexoEnum.MASCULINO.displayValue))
            return SexoEnum.MASCULINO;
        else if (value.equals(SexoEnum.FEMININO.displayValue))
            return SexoEnum.FEMININO;
        
        return null;
    }
}
