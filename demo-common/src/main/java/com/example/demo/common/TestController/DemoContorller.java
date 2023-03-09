package com.example.demo.common.TestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoContorller
 * @Author yu.zhang
 * @Description
 * @Date 2023/2/7 9:47
 **/
@RestController
@RequestMapping("/serviceAgent/rest/otms/TerminationPointRetrieval")
public class DemoContorller {




    @RequestMapping("/getTerminationPoint")
    public String demoRest(){
        return "{\n" +
                "\t\"responseHeader\": {\n" +
                "\t\t\"result\": 1,\n" +
                "\t\t\"desc\": \"SUCCESS\",\n" +
                "\t\t\"headExtensions\": {\n" +
                "\t\t\t\"traceId\": \"3ff5d7961eee6e21\"\n" +
                "\t\t},\n" +
                "\t\t\"services\": \"S1000\"\n" +
                "\t},\n" +
                "\t\"tp\": {\n" +
                "\t\t\"tpRef\": \"F8B890F7E8755FB205EEE7683A6827DA\",\n" +
                "\t\t\"aliasName\": \"IN\",\n" +
                "\t\t\"userLabel\": \"\",\n" +
                "\t\t\"connectionState\": null,\n" +
                "\t\t\"direction\": 3,\n" +
                "\t\t\"type\": \"ots\",\n" +
                "\t\t\"domain\": null,\n" +
                "\t\t\"bindingObject\": null,\n" +
                "\t\t\"isFixedBinding\": null,\n" +
                "\t\t\"clientServiceTypeList\": \"-1\",\n" +
                "\t\t\"clientServiceContainer\": null,\n" +
                "\t\t\"clientServiceMappingMode\": null,\n" +
                "\t\t\"transmissionParametersList\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"layerRate\": 47,\n" +
                "\t\t\t\t\"parameterList\": {\n" +
                "\t\t\t\t\t\"ClientServiceTypeList\": \"NA\",\n" +
                "\t\t\t\t\t\"MaxoutputPower\": \"NA\",\n" +
                "\t\t\t\t\t\"MaxoutputPowerLowerThreshold\": \"NA\",\n" +
                "\t\t\t\t\t\"MaxoutputPowerUpperThreshold\": \"NA\",\n" +
                "\t\t\t\t\t\"ModuleType\": \"NA\",\n" +
                "\t\t\t\t\t\"PortType\": \"optical\",\n" +
                "\t\t\t\t\t\"TransmissionDistance\": \"NA\",\n" +
                "\t\t\t\t\t\"attenuation\": \"NA\",\n" +
                "\t\t\t\t\t\"attenuationLowerThreshold\": \"NA\",\n" +
                "\t\t\t\t\t\"attenuationUpperThreshold\": \"NA\",\n" +
                "\t\t\t\t\t\"gain\": \"NA\",\n" +
                "\t\t\t\t\t\"gainLowerThreshold\": \"NA\",\n" +
                "\t\t\t\t\t\"gainUpperThreshold\": \"NA\",\n" +
                "\t\t\t\t\t\"inputRefrencePower\": \"NA\",\n" +
                "\t\t\t\t\t\"laserAutoShutDown\": \"NA\",\n" +
                "\t\t\t\t\t\"outputRefrencePower\": \"NA\",\n" +
                "\t\t\t\t\t\"overLoadPoint\": \"NA\",\n" +
                "\t\t\t\t\t\"sensitivity\": \"NA\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"layerRate\": 49,\n" +
                "\t\t\t\t\"parameterList\": {}\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"vendorExtensions\": {\n" +
                "\t\t\t\"type\": \"ots\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}" ;
    }





}
