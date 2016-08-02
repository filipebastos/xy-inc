package com.xyinc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xyinc.model.PontoInteresse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class PontoInteresseServiceTest {

    private static ServiceLocator serviceLocator;
    
    private static IPontoInteresseService service;
    
    @BeforeClass
    public static void init() {
        serviceLocator = ServiceLocator.getInstance();
    }

    @Before
    public void setUp() {
    	service = serviceLocator.getPontoInteresseService();
    }

    @Test
    public void verificarServico() {
        Assert.assertNotNull(serviceLocator);
        Assert.assertNotNull(service);
    }

	@Test
	public void testIncluir() {
		
		int qdtAntes = service.listarTodos().size();

		service.incluir(new PontoInteresse("POI-00", 14, 5));

		int qdtDepois = service.listarTodos().size();
		
		assertFalse(service.listarTodos().isEmpty());		
		assertEquals(qdtAntes + 1, qdtDepois);
	}

	@Test(expected = XyIncException.class)
	public void testIncluirComCoordenadasNegativas() {
		service.incluir(new PontoInteresse("Error-01", -10, -5));
	}

	@Test
	public void testAlterar() {
		Long id = null;
		
		PontoInteresse pontoInteresse = new PontoInteresse("POI-01", 40, 15);
		service.incluir(pontoInteresse);
		id = pontoInteresse.getId();
		assertNotNull(id);
		
		PontoInteresse pontoInteresseUpdated = service.buscarPeloId(id);
		pontoInteresseUpdated.setNome("POI-02");
		pontoInteresseUpdated.setCoordenadaX(10);
		pontoInteresseUpdated.setCoordenadaY(10);
		service.alterar(pontoInteresseUpdated);

		assertFalse(service.buscarPeloId(id).getNome().equals("POI-01"));
		assertTrue(service.buscarPeloId(id).getNome().equals("POI-02"));
	}

	@Test
	public void testRemover() {
		
		PontoInteresse pontoInteresse = new PontoInteresse("POI-03", 42, 13);
		service.incluir(pontoInteresse);
		
		int qdtAntes = service.listarTodos().size();
		service.remover(pontoInteresse);
		int qdtDepois= service.listarTodos().size();
		
		assertEquals(qdtAntes - 1, qdtDepois);
	}

	@Test
	public void testListarPorProximidade() {
				
		PontoInteresse lanchonete = new PontoInteresse("Lanchonete", 27, 12);		
		service.incluir(lanchonete);
		
		PontoInteresse posto = new PontoInteresse("Posto", 31, 18);		
		service.incluir(posto);
		
		PontoInteresse joalheria = new PontoInteresse("Joalheria", 15, 12);		
		service.incluir(joalheria);
		
		PontoInteresse floricultura = new PontoInteresse("Floricultura", 19, 21);
		service.incluir(floricultura);
		
		PontoInteresse pub = new PontoInteresse("Pub", 12, 8);
		service.incluir(pub);
		
		PontoInteresse supermercado = new PontoInteresse("Supermercado", 23, 6);
		service.incluir(supermercado);
		
		PontoInteresse churrascaria = new PontoInteresse("Churrascaria", 28, 2);
		service.incluir(churrascaria);
		
		Collection<PontoInteresse> pois = service.listarPorProximidade(20, 10, 10);
		
		assertTrue(pois.contains(lanchonete));
		assertFalse(pois.contains(posto));
		assertTrue(pois.contains(joalheria));
		assertFalse(pois.contains(floricultura));
		assertTrue(pois.contains(pub));
		assertTrue(pois.contains(supermercado));
		assertTrue(pois.contains(churrascaria));
	}

}
