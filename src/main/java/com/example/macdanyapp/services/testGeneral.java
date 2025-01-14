package com.example.macdanyapp.services;

import com.example.macdanyapp.entitys.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class testGeneral
{
    public static void main(String[] args) throws SQLException {

        ClienteService clienteService = new ClienteService();
        // CREAR CLIENTE(Funciona)
        //Cliente nuevoCliente = new Cliente("Maximiliano","Tosi","1150114622","Castex 894");
        //clienteService.insertCliente(nuevoCliente);

        //TRAER CLIENTE POR NOMBRE Y APELLIDO(Funciona)
        //Cliente nuevoCliente2=clienteService.traerClientePorNombreYApellido("Diego","Maradona");
        //System.out.println(nuevoCliente2.toString());

        //TRAER CLIENTE POR ID(Funciona)
        //Cliente nuevoCliente3=clienteService.traerCliente(3);


        //ELIMINAR CLIENTE(Funciona)
        //clienteService.eliminarCliente(4);

        //MODIFICAR CLIENTE (Funciona)
        //long idCliente = 1; // ID del cliente a actualizar
        // String nuevoNombre = "Diego";
        //String nuevoApellido = "Maradona";
        //String nuevoDomicilio = "NuevaDireccion";
        //String nuevoNumeroDeTelefono = "987654321";
        //clienteService.modificarCliente(idCliente,nuevoNombre, nuevoApellido, nuevoDomicilio, nuevoNumeroDeTelefono);


        ProveedorService proveedorService = new ProveedorService();

        //CREAR PROVEEDOR (Funciona)
         //Proveedor nuevoProveedor=new Proveedor("OCA","1150114622", 3500F);
         //proveedorService.insertProveedor(nuevoProveedor);

         //TRAER PROVEEDOR POR ID (Funciona)

        //proveedorService.traerProveedor(1);

        //TRAER PROVEEDOR POR NOMBRE (Funciona)

        //proveedorService.traerProveedorPorNombre("OCA");

        //ELIMINAR PROVEEDOR (Funciona)
        //proveedorService.eliminarProveedor(1);

        //MODIFICAR PROVEEDOR (Funciona)
        //proveedorService.modificarProveedor(2L,"FLYBONDI","1150114622");


        //INSERTAR VAJILLA (Funciona)
        //VajillaService vajillaService = new VajillaService();
        //Vajilla nuevaVajilla = new Vajilla("","",40f,"",);
        //vajillaService.insertVajilla(nuevaVajilla);

        //TRAER VAJILLA POR TIPO Y MODELO (Funciona)
        //vajillaService.traerVajillaPorTipoYModelo("VASO","");

        //TRAER VAJILLA POR ID (Funciona)
        //vajillaService.traerVajilla(1);

        //MODIFICAR VAJILLA (Funciona)
        //vajillaService.modificarVajilla(1L,"VASO","TRAGO LARGO","","",100F);

        //ELIMINAR VAJILLA (Funciona)
        //vajillaService.eliminarVajilla(1);


        //INSERTAR ALQUILER (Funciona)

        AlquilerService alquilerService = new AlquilerService();
        //List<Detalle_Alquiler> alquileres=new ArrayList<>();

        //Alquiler nuevoAlquiler=new Alquiler(LocalDate.of(2024,11,15),LocalDate.of(2024,11,16), LocalTime.of(18,0,0),LocalTime.of(23,0,0),clienteService.traerCliente(1),1,2000F,20000F,Estado.ACTIVO,alquileres,null,"Pedido de Carlitos Tevez");
        //Multa multa=new Multa(3000F,LocalDate.of(2024,11,23),nuevoAlquiler);
        //nuevoAlquiler.setMulta(multa);
        //alquilerService.inserAlquiler(nuevoAlquiler);


        //TRAER ALQUILER (Funciona)
         //Alquiler alquiler=alquilerService.traerAlquiler(2);

        //TRAER ALQUILER POR FECHA (Funciona)
        //alquilerService.traerAlquilerPorFecha(LocalDate.of(2024,11,11),LocalDate.of(2024,11,15));

        //ELIMINAR ALQUILER (Funciona)
        //alquilerService.eliminarAlquiler(1);

        //MODIFICAR ALQUILER (NO FUNCIONA, HAY QUE VERIFICAR COMO LE INDICAMOS QUE ALQUILER QUEREMOS MODIFICAR)
        //alquilerService.modificarAlquiler(195f,alquilerService.traerAlquiler(8).getCliente());


        //INSERTAR DETALLE ALQUILER (Funciona)

        //Vajilla nuevaVajilla=vajillaService.traerVajilla(2);

        DetalleAlquilerService detalleAlquilerService = new DetalleAlquilerService();
        //Detalle_Alquiler detalleAlquiler=new Detalle_Alquiler(100,30F,alquiler,nuevaVajilla);
        //detalleAlquilerService.insertarDetalleAlquiler(detalleAlquiler);

        //TRAER DETALLE ALQUILER (Funciona)

        //detalleAlquilerService.traerDetalleAlquiler(1);

        //MODIFICAR DETALLE ALQUILER (Funciona)
        //detalleAlquilerService.modificarDetalleAlquiler(1L,30,100F,alquiler,nuevaVajilla);

        //ELIMINAR DETALLE ALQUILER (Funciona)
        //detalleAlquilerService.eliminarDetalleAlquiler(2);

        

        //INSERTAR STOCK(Funciona)

        StockService stockService = new StockService();
        //Stock stock=new Stock(20,vajillaService.traerVajilla(2));
        //stockService.inserStock(stock);

        //TRAER STOCK(Funciona)

        //stockService.traerStock(2);

        //MODIFICAR STOCK (Funciona)

        //stockService.modificarStock(100,1);

        //ELIMINAR STOCK(Funciona)

        //stockService.eliminarStock(2);


        //INSERTAR PAGO(Funciona)
        PagoService pagoService = new PagoService();
        //Pago pago=new Pago(alquilerService.traerAlquiler(2),200F,50F,"Pago con efectivo",LocalDate.of(2024,5,22),LocalDate.of(2024,5,27));
        //pagoService.insertPago(pago);

        //TRAER PAGO(Funciona)
        //pagoService.traerPago(2);

        //MODIFICAR PAGO(Funciona)
        //pagoService.modificarPago(2,300,0,"Pago con tarjeta",LocalDate.of(2024,5,4),LocalDate.of(2024,5,5));

        //ELIMINAR PAGO(Funciona)
        //pagoService.eliminarPago(2);

        //INSERTAR INVENTARIO DE MOVIMIENTOS(Funciona)
        InventarioMovimientosService inventarioMovimientosService = new InventarioMovimientosService();
        //InventarioMovimientos inventarioMovimientos=new InventarioMovimientos(vajillaService.traerVajilla(2),50, TipoMovimiento.entrada,LocalDate.of(2024,5,22));
        //inventarioMovimientosService.insertarInventarioMovimientos(inventarioMovimientos);

        //TRAER INVENTARIO DE MOVIMIENTOS(Funciona)

        //inventarioMovimientosService.traerInventarioMovimientos(2);

        //MODIFICAR INVENTARIO DE MOVIMIENTOS(Funciona)
        //inventarioMovimientosService.modificarInventarioMovimientos(2,10, String.valueOf(TipoMovimiento.salida),LocalDate.of(2024,5,10));

        //ELIMINAR INVENTARIO DE MOVIMIENTOS(Funciona)
        //inventarioMovimientosService.eliminarInventarioMovimientos(2);


        //INSERTAR HISTORIAL DE ALQUILERES(Funciona)

        HistorialAlquileresService historialAlquileresService = new HistorialAlquileresService();

        //historialAlquileresService.insertHistorialAlquiler(alquilerService.traerAlquiler(2));
        //List<DetalleAlquiler> vajillaAlquilada =new ArrayList<>();
        //Alquiler alquiler=new Alquiler(LocalDate.of(2024,11,15),LocalDate.of(2024,11,16), LocalTime.of(18,0,0),LocalTime.of(23,0,0),clienteService.traerCliente(1),1,2000F,20000F,Estado.FINALIZADO,vajillaAlquilada,null,"Pedido de Lionel Messi");
        //alquilerService.inserAlquiler(alquiler);

        //historialAlquileresService.insertHistorialAlquiler(alquilerService.traerAlquiler(4));

        //TRAER HISTORIAL DE ALQUILERES(Funciona)

        //historialAlquileresService.traerHistorialDeAlquileres();


        //INSERTAR GASTO EXTERNO(Funciona)
        GastoExternoService gastoExternoService = new GastoExternoService();
        //GastoExterno gastoExterno=new GastoExterno("Alquiler a Messi",200F,LocalDate.of(2022,11,21),alquilerService.traerAlquiler(2));
        //gastoExternoService.insertGastoExterno(gastoExterno);


        //TRAER GASTO EXTERNO(Funciona)

        //gastoExternoService.traerGastoExterno(2);

        //MODIFICAR GASTO EXTERNO(Funciona)

        //gastoExternoService.modificarProveedor(2,"Alquiler para aguero",500f,LocalDate.of(2022,11,21));


        //ELIMINAR GASTO EXTERNO(Funciona)
        //gastoExternoService.eliminarGastoExterno(2);


        //INSERTAR USUARIO(Funciona)
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario=new Usuario("mati","1234",TipoDeUsuario.ADMINISTRADOR);
        //usuarioService.insertUsuario(usuario);


        //TRAER USUARIO (Funciona)
        //usuarioService.traerUsuario(usuario.getNombreDeUsuario());

        //MODIFICAR USUARIO (Funciona)
        //usuarioService.modificarStock(1,"mat","123",TipoDeUsuario.ADMINISTRADOR);



        //TIPO DE VAJILLA
        TipoDeVajillaService tipoDeVajillaService=new TipoDeVajillaService();

        //TipoDeVajilla tipoDeVajilla=new TipoDeVajilla("CUCHILLOS");
        //tipoDeVajillaService.insertTipoDeVajilla(tipoDeVajilla);

         VajillaService vajillaService = new VajillaService();
        //Vajilla nuevaVajilla = new Vajilla("","",500f,"",tipoDeVajillaService.traerTipoDeVajilla("TENEDORES"));
        //vajillaService.insertVajilla(nuevaVajilla);

        //Stock stock=new Stock(1000,vajillaService.traerVajillaPorTipo(3));
        //stockService.inserStock(stock);
        LocalDate fecha=LocalDate.of(2024,11,15);
        LocalDate fecha2=LocalDate.of(2024,11,20);
        LocalTime hora=LocalTime.of(22,0,0);
        LocalTime hora2=LocalTime.of(23,0,0);

       //alquilerService.traerAlquileresPorFechaYEstado(fecha,fecha2,Estado.ACTIVO);

        LocalDate fechaActual=LocalDate.now();

        //alquilerService.traerAlquilerYActivarlo(Estado.PENDIENTE,fechaActual);

        //alquilerService.modificarAlquilerCompleto(9,fecha,fecha2,hora,hora2,3,3,250F,1790F,Estado.ACTIVO);

        //Vajilla vajilla=new Vajilla(null,"BLANCO",100F,null,tipoDeVajillaService.traerTipoDeVajilla("MANTELES"));
        //vajillaService.insertVajilla(vajilla);


        //vajillaService.traerListaVajilla();
        //tipoDeVajillaService.traerTipoDeVajillaPorId(1);

        alquilerService.modificarEstadoAlquiler(Estado.ACTIVO,10);


    }
}

