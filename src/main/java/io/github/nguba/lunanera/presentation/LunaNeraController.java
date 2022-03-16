package io.github.nguba.lunanera.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class LunaNeraController {

//    private final AbstractMonitor monitor;
//
//    public LunaNeraController(final AbstractMonitor monitor) {
//        this.monitor = monitor;
//    }
//
//    private final Path basePath = Paths.get("./src/main/resources/upload/");
//
//    @GetMapping(path = "/devices")
//    public Flux<Actuator> devices() {
//        return Flux.fromIterable(monitor.getActuators());
//    }
//
//    @PostMapping("batch")
//    public Mono<Void> uploadBatch(@RequestPart("fileToUpload") Flux<FilePart> filePartMono) {
//        return filePartMono
//                .doOnNext(fp -> System.out.println
//                        ("Received File : " + fp.filename()))
//                .flatMap(fp -> fp.
//                        transferTo(basePath.resolve(fp.filename())))
//                .doOnComplete(() -> System.out.println("Done")).then();
//    }
//
//    private List<String> processAndGetLinesAsList(String string) {
//        Supplier<Stream<String>> streamSupplier = string::lines;
//        List<String> strings = streamSupplier.get().collect(Collectors.toList());
//        System.out.println(strings);
//        return strings;
//    }
}
