package com.pet.agenda.steps;

import com.pet.model.Person;
import com.pet.model.User;
import com.pet.repository.IPersonRepository;
import com.pet.repository.IUserRepository;
import com.pet.service.UsuarioServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class IngresoUsuarioSteps {
    private String nombreUsuarioIngresado;
    private String mensajeResultado;
    @Autowired
    private  IUserRepository userRepository;
    @Autowired
    private  IPersonRepository personRepository;
    @Given("^que existe un usuario con nombre \"([^\"]*)\" en la base de datos$")
    public void existeUsuarioEnBD(String nombreUsuario) {
        Person person = new Person(1L,"Amelia", "Duran", "1234567890", LocalDate.now());
        User user = new User();
        user.setUsername(nombreUsuario);
        user.setPassword("12345678");
        user.setPerson(person);
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            this.personRepository.save(person);
            this.userRepository.save(user);
        }
    }
    @When("^el usuario ingresa el nombre \"([^\"]*)\"$")
    public void usuarioIngresaNombre(String nombreUsuario) {
        this.nombreUsuarioIngresado = nombreUsuario;
    }

    @Then("^el sistema muestra un mensaje de bienvenida$")
    public void sistemaMuestraMensajeBienvenida() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080");
        WebElement mensajeBienvenida = driver.findElement(By.id("mensaje-bienvenida"));
        Assert.assertEquals("¡Bienvenido!", mensajeBienvenida.getText());
        driver.quit();
    }
    @Then("^el sistema muestra un mensaje de usuario no encontrado$")
    public void sistemaMuestraMensajeUsuarioNoEncontrado() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080");
        WebElement nombreUsuarioInput = driver.findElement(By.id("nombre-usuario"));
        nombreUsuarioInput.sendKeys("usuario_no_existente");
        WebElement enviarButton = driver.findElement(By.id("enviar-button"));
        enviarButton.click();
        WebElement mensajeUsuarioNoEncontrado = driver.findElement(By.id("mensaje-usuario-no-encontrado"));
        Assert.assertEquals("Usuario no encontrado", mensajeUsuarioNoEncontrado.getText());
        driver.quit();
    }
}

