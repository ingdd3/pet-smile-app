package com.pet.agenda.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistroHoraMedicaSteps {
    private String nombreMascota;
    private String fecha;
    private String hora;
    private String mensajeResultado;
    @Given("^que existe una mascota con nombre \"([^\"]*)\" en la base de datos$")
    public void existeMascotaEnBD(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }
    @When("^el usuario registra una hora medica para la mascota \"([^\"]*)\" con fecha \"([^\"]*)\" y hora \"([^\"]*)\"$")
    public void usuarioRegistraHoraMedica(String nombreMascota, String fecha, String hora) {
        this.nombreMascota = nombreMascota;
        this.fecha = fecha;
        this.hora = hora;
    }
    @Then("^el sistema muestra un mensaje de éxito en el registro de la hora medica$")
    public void sistemaMuestraMensajeExito() {
        this.mensajeResultado = "Mensaje de éxito";
    }
    @Then("^el sistema muestra un mensaje de error indicando que la mascota no existe$")
    public void sistemaMuestraMensajeMascotaNoExiste() {
        this.mensajeResultado = "Mensaje de error: Mascota no encontrada";
    }
    @Then("^el sistema muestra un mensaje de error indicando que la fecha y hora son inválidas$")
    public void sistemaMuestraMensajeFechaHoraInvalidas() {
        this.mensajeResultado = "Mensaje de error: Fecha y hora inválidas";
    }
}
