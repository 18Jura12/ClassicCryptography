package com.java.crypto;

import com.java.crypto.services.InitializeService;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.text.ParseException;

@SpringBootApplication
public class CryptoApplication {

	private final ApplicationContext ctx;
        
        public native void helloC();
    
        static {
            System.loadLibrary("MatrixOperations");
        }

	public CryptoApplication(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	public static void main(String[] args) {

		SpringApplication.run(CryptoApplication.class, args);
                
	}

	@PostConstruct
	public void initializationDev() throws Exception {

		InitializeService initializeService = (InitializeService) ctx.getBean("InitializeService");
		initializeService.initialize();

	}

}
