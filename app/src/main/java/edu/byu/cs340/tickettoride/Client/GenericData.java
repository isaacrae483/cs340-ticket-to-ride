package edu.byu.cs340.tickettoride.Client;

public class GenericData {
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;

    public GenericData(String methodName, Class<?>[] paramTypes, Object[] paramValues){
        _className = "edu.byu.cs340.tickettoride.Client.ServerProxy";
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
    }

    public String get_className() {
        return _className;
    }

    public String get_methodName() {
        return _methodName;
    }

    public Class<?>[] get_paramTypes() {
        return _paramTypes;
    }

    public Object[] get_paramValues() {
        return _paramValues;
    }
}
