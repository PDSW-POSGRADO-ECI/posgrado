/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.services;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import proyecto.services.impl.ServiciosReporteImpl;

/**
 *
 * @author OscarAlba
 */
public class ServiciosReporteFactory {
    
     private static ServiciosReporteFactory instance = new ServiciosReporteFactory();
    
    private static Injector injector;
    //private static Injector testingInjector;
    
    private ServiciosReporteFactory(){
        injector = createInjector(new XMLMyBatisModule() {

                    @Override
                    protected void initialize() {
                        install(JdbcHelper.PostgreSQL);                        
                        setClassPathResource("mybatis-configDatabase.xml");                        
                        //bind(ServiciosReporte.class).to(ServiciosReporteImpl.class);
                        //bind(DaoComentario.class).to(MyBatisDaoComentario.class);
                        
                    }

                }
                
        );
        
        /*testingInjector = createInjector(new XMLMyBatisModule() {

                    @Override
                    protected void initialize() {
                        install(JdbcHelper.MySQL);                        
                        setClassPathResource("h2-mybatis-config.xml");                        
                        //bind(ServiciosSuscripciones.class).to(ServiciosSuscripcionesImpl.class);
                        //bind(DaoComentario.class).to(MyBatisDaoComentario.class);
                        
                    }

                }
                
        );
        public ServiciosReporte getServiciosReporteForTesting(){
        return testingInjector.getInstance(ServiciosReporte.class);   
    }*/
        
    }

    public ServiciosReporte getServiciosReporte(){
        return injector.getInstance(ServiciosReporte.class);   
    }
    
    
    
    public static ServiciosReporteFactory getInstance(){
        return instance;
    }
    
    
    public static void main(String a[]) throws ExceptionServiciosReporte{
       
    }
    
}
