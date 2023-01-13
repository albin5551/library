package librarymanagement.library.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import librarymanagement.library.entity.Request;
import librarymanagement.library.form.RequestForm;
import librarymanagement.library.service.RequestServic;
import librarymanagement.library.view.RequestListview;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestServic requestServic;


    @PostMapping
    public RequestListview add(@Valid @RequestBody RequestForm form){
        return requestServic.add(form);
    }
        @GetMapping
        public Collection<Request>list(){
            return requestServic.list();
        }

        @PutMapping("/{reqId}")
        public RequestListview update(@PathVariable("reqId") Integer reqId,
        @Valid @RequestBody RequestForm form){
            return requestServic.update(reqId, form);
        }
    }

