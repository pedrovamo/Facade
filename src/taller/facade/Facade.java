/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller.facade;

import AdapterPackage.Adapter;
import AdapterPackage.Conductor;
import AdapterPackage.Pasajero;
import AdapterPackage.Usuario;
import Composite.Vehiculo_hoja;
import Composite.composite;
import Composite.interfaceGrupo;
import Flyweight.FlyWeight;
import Flyweight.FlyWeightFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Nikolas
 */
public class Facade {

    PagoGrupo G1 = new PagoGrupo();
    ReservaGrupo G2 = new ReservaGrupo();
    ArrayList<Usuario> componentes = new ArrayList();
    private static Facade facade;
    FlyWeightFactory FF = new FlyWeightFactory();
    composite GrupoBase = new composite("GrupoBase");

    public Facade() {
        Usuario user = new Adapter("Hola", "123");
        this.componentes.add(user);
    }

    public static Facade getFacade() {
        if (facade == null) {
            facade = new Facade();
        }
        return facade;
    }

    public void CrearReserva(String nombre, String id, String fecha, String concepto, String lugar) {
        Reserva reserva = new Reserva(nombre, id, fecha, concepto, lugar);
                System.out.println("RR");
        G2.AñadirGrupito(reserva);

    }

    public void AñadirReserva(Reserva r) {
        G2.AñadirGrupito(r);

    }

    public void EliminarReserva(Reserva r) {
        G2.EliminarAlGrupito(r);
    }

    public void EliminarReservaGrupo(Reserva r) {
        G2.EliminarAlGrupito(r);
    }

    public void ModificarLugarReserva(String id, String Lugar) {
        G2.ModificarLugar(id, Lugar);
    }

    public String LeerReserva(String id) {
        return G2.LeerReserva(id);
    }

    public void CrearPago(String nombre, String id, int pago, String fecha, String concepto) {
        Pago pago1 = new Pago(nombre, id, pago, fecha, concepto);
        G1.AñadirGrupito(pago1);

    }

    public void AñadirPago(Pago r) {
        G1.AñadirGrupito(r);
    }

    public void EliminarPago(Pago r) {
        G1.EliminarAlGrupito(r);
    }

    public String LeerPago(String id) {
        return G1.LeerPago(id);
    }

    public void Modificar_ConceptoPago(String id, String Concepto) {
        G1.ModificarConcepto(id, Concepto);
    }

    public FlyWeight Getpago(int i, Usuario u) {
        return u.getFactory().Getpago(i);
    }

    public String verPagosP(String nombreU) {
        String info = "";
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).getUsuario().equalsIgnoreCase(nombreU)) {
                info = componentes.get(i).getPagos(nombreU);
            }
        }
        return info;
    }

    public String verPagosC(String nombreC) {
        String info = "";
        info = FF.toStringPagosConductor(nombreC);
        return info;
    }

    public void ModificarPago(int index, Usuario user, String Varios1, String Varios2, String Varios3) {

        user.getFactory().ModificarPago(index, Varios1, Varios2, Varios3);

    }

    public void Acceso(String Accion, String NombreUser, String PassUser, String Para) throws NoSuchMethodException {
        for (int i = 0; i < this.componentes.size(); i++) {
            if (NombreUser.compareTo(componentes.get(i).getUsuario()) == 0 && PassUser.compareTo(componentes.get(i).getPassword()) == 0) {

                for (Object c : componentes) {

                    Method[] metodos = componentes.get(i).getClass().getMethods();
                    Method[] metodosFacade = Facade.class.getMethods();

                    try {

                        Method methodcall1 = componentes.get(i).getClass().getDeclaredMethod("Permisos");
                        String cadena = (String) methodcall1.invoke(c);
                        String[] parts = cadena.split(",");
                        String[] para = Para.split("-");

                        for (String part : parts) {
                            for (Method metodosFacade1 : metodosFacade) {
                                if (part.contains(metodosFacade1.getName()) && metodosFacade1.getName().contains(Accion)) {
                                    if (Accion.equals("CrearReserva")) {
                                        System.out.println("CrearR");
                                        CrearReserva(para[0], para[1], para[2], para[3], para[4]);
                                    }
                                } else if (Accion.equals("LeerReserva")) {
                                    LeerReserva(para[0]);

                                } else if (Accion.equals("ModificarLugarReserva")) {
                                    ModificarLugarReserva(para[0], para[1]);

                                } else if (Accion.equals("CrearPago")) {
                                    CrearPago(para[0], para[1], Integer.parseInt(para[2]), para[3], para[4]);

                                } else if (Accion.equals("EliminarPago")) {
                                    EliminarPago(G1.ObtenerPago(para[0]));

                                } else if (Accion.equals("EliminarReserva")) {
                                    EliminarReserva(G2.ObtenerReserva(para[0]));

                                } else if (Accion.equals("Modificar_ConceptoPago")) {
                                    Modificar_ConceptoPago(para[0], para[1]);

                                } else if (Accion.equals("LeerPago")) {
                                    LeerPago(para[0]);

                                } else if (Accion.equals("Consultar_Usuario")) {
                                    Consultar_Usuario(para[0]);

                                } else if (Accion.equals("eliminar_Usuario")) {
                                    eliminar__Usuario(para[0], para[1]);

                                } else if (Accion.equals("mod_Usuario")) {
                                    mod_Usuario(para[0], para[1], para[2]);

                                } else if (Accion.equals("crearCredito")) {
                                    crearCredito(Integer.parseInt(para[0]), para[1], para[2], Float.parseFloat(para[3]), para[4]);

                                } else if (Accion.equals("crearEfectivo")) {
                                    crearEfectivo(Integer.parseInt(para[0]), para[1], para[2], Float.parseFloat(para[3]), para[4]);
                                    break;

                                } else if (Accion.equals("EliminarPago")) {

                                    componentes.get(i).getFactory().EliminarPago(componentes.get(i).getFactory().Getpago(Integer.parseInt(para[0])));

                                } else if (Accion.equals("ModificarPago")) {
                                    componentes.get(i).getFactory().ModificarPago(Integer.parseInt(para[0]), para[1], para[2], para[3]);

                                } else if (Accion.equals("VerPagos")) {
                                    componentes.get(i).getFactory().toString();

                                } else if (Accion.equals("crearAgrupacion")) {
                                    crearAgrupacion(para[0]);

                                } else if (Accion.equals("CrearVehiculo")) {

                                    AgregarElemento(CrearVehiculo(para[0], para[1], para[2], Integer.parseInt(para[3]), para[4]));

                                } else if (Accion.equals("AgregarAgrupacion")) {

                                    AgregarElemento(Getelemento(para[0]));

                                } else if (Accion.equals("EliminarElemento")) {

                                    EliminarElemento(Getelemento(para[0]));

                                } else if (Accion.equals("VerTransporte")) {
                                    VerTransporte(para[0]);

                                }
//break;
                            }
                            break;
                        }
                        break;
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void Crear_Usuario(String User, String pass, String Tipo) {
        if (Tipo.equals("Pasajero")) {
            Usuario usuario = new Pasajero(User, pass);
            usuario.setTipo_Usuario(Tipo);
            componentes.add(usuario);

        } else if (Tipo.equals("Conductor")) {
            Usuario usuario = new Conductor(User, pass);
            usuario.setTipo_Usuario(Tipo);
            componentes.add(usuario);
        } else if (Tipo.equals("Administrador")) {
            Usuario usuario = new Adapter(User, pass);
            usuario.setTipo_Usuario(Tipo);
            componentes.add(usuario);

        } else {
            System.out.print("No hizo match tipo");
        }
    }

    public String Consultar_Usuario(String User) {
        String info = "";
        for (int i = 0; i < componentes.size(); i++) {
            System.out.println("1");
            if (componentes.get(i).getUsuario().equalsIgnoreCase(User)) {
                System.out.println("2");
                info = componentes.get(i).getUsuario() + "," + componentes.get(i).getPassword() + "," + componentes.get(i).getTipo_Usuario() + ",";
            }
        }
        return info;
    }

    public void eliminar__Usuario(String User, String pass) {
        int a = 0;
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).getUsuario().equalsIgnoreCase(User) && componentes.get(i).getPassword().equalsIgnoreCase(pass)) {
                a = i;
            }
        }
        componentes.remove(a);
    }

    public void mod_Usuario(String user, String newUser, String pass) {
        int a = 0;
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).getUsuario().equalsIgnoreCase(user)) {
                a = i;
            }
        }
        componentes.get(a).setUsuario(newUser);
        componentes.get(a).setPassword(pass);
    }

    public void crearCredito(int id, String nombrePasajero, String nombreConductor, float monto, String otros) {
        FF.CrearPagoTarjeta(id, nombrePasajero, nombreConductor, monto, otros);
        FlyWeight F = FF.Getpago(id);
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).getUsuario().equalsIgnoreCase(nombrePasajero)) {
                componentes.get(i).AñadirPago(F);
            }
        }
    }

    public void crearEfectivo(int id, String nombrePasajero, String nombreConductor, float monto, String otros) {
        FF.CrearPagoEfectivo(id, nombrePasajero, nombreConductor, monto, otros);
        FlyWeight F = FF.Getpago(id);
        int a = 0;
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).getUsuario().equalsIgnoreCase(nombrePasajero)) {
                a = i;
            }
        }
        componentes.get(a).AñadirPago(F);
    }

    public String leerEoC(String nombreU, int id) {
        String info = "";
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).getUsuario().equalsIgnoreCase(nombreU)) {
                info = componentes.get(i).getPago(id);
            }
        }
        return info;
    }

    public composite crearAgrupacion(String NombreGrupo) {
        composite grupo = new composite(NombreGrupo);
        GrupoBase.Añadir(grupo);
        return grupo;

    }

    public Vehiculo_hoja CrearVehiculo(String Nombre, String Tipo, String Placa, int capacidad, String Referencia) {
        Vehiculo_hoja carro = new Vehiculo_hoja(Nombre, Tipo, Placa, capacidad, Referencia);
        return carro;

    }

    public void AgregarElemento(interfaceGrupo componente) {
        this.GrupoBase.Añadir(componente);

    }

    public void EliminarElemento(interfaceGrupo componente) {
        this.GrupoBase.Eliminar(componente);

    }

    public interfaceGrupo Getelemento(String Nombre) {
        return this.GrupoBase.Getelemento(Nombre);

    }

    public String VerTransporte(String Nombre) {
        return GrupoBase.getNombreGrupo();

    }

}
