package io.github.nguba.lunanera.application;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import io.github.nguba.lunanera.domain.command.CommandFactory;
import io.github.nguba.lunanera.infrastructure.DomainEventPublisher;
import io.github.nguba.lunanera.infrastructure.EventPublisher;
import io.github.nguba.lunanera.infrastructure.ModbusFactory;
import io.github.nguba.lunanera.presentation.LunaNeraController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackageClasses = {LunaNeraController.class})
@EnableConfigurationProperties({LunaNeraConfig.class, LunaNeraConfig.SerialConfig.class, LunaNeraConfig.Pid.class})
public class LunaNeraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunaNeraApplication.class, args);
    }

    @Bean
    DomainEventPublisher publisher(ApplicationEventPublisher publisher) {
        return new DomainEventPublisher(publisher);
    }

    @Bean
    ModbusMaster master(LunaNeraConfig config) throws Exception {
        return new ModbusFactory().createMaster(config.getSerial().getDevice());
    }

    @Bean
    ProcessValueMeasurementRepository temperatureRepository(DataSource ds) {
        return new ProcessValueMeasurementRepository(ds);
    }

    @Bean
    CommandService commandService() {
        return new CommandService();
    }

    @Bean
    CommandFactory commandFactory(EventPublisher publisher) {
        return new CommandFactory(publisher);
    }

    @Bean
    ContextStartedListener startupListener(LunaNeraConfig config, PidControllerRepository pidControllerRepository,
                                           ModbusService service, ModbusMaster master, CommandService commands,
                                           CommandFactory factory) {
        return new ContextStartedListener(config, pidControllerRepository, service, master, commands, factory);
    }

    @Bean
    PidControllerStatusListener pidControllerStatusListener(PidControllerStatusRepository repository) {
        return new PidControllerStatusListener(repository);
    }

    @Bean
    ProcessValueMeasuredListener processValueMeasuredListener(ProcessValueMeasurementRepository repository) {
        return new ProcessValueMeasuredListener(repository);
    }

    @Bean
    PidControllerRepository pidControllerRepository() {
        return new PidControllerRepository();
    }

    @Bean
    SetpointMeasurementRepository setpointMeasurementRepository(DataSource dataSource) {
        return new SetpointMeasurementRepository(dataSource);
    }

    @Bean
    SetpointReceivedListener setpointReceivedListener(SetpointMeasurementRepository repository) {
        return new SetpointReceivedListener(repository);
    }

    @Bean
    ModbusService modbusService() {
        return new ModbusService();
    }

    @Bean
    PidControllerStatusRepository controllerStatusRepository(DataSource dataSource) {
        return new PidControllerStatusRepository(dataSource);
    }
}
