package hu.nje.eloadas_beadando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import soapclient.MNBArfolyamServiceSoap;
import soapclient.MNBArfolyamServiceSoapGetCurrentExchangeRatesStringFaultFaultMessage;
import soapclient.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import soapclient.MNBArfolyamServiceSoapGetInfoStringFaultFaultMessage;
import soapclient.MNBArfolyamServiceSoapImpl;

@SpringBootApplication
@Controller
public class EloadasBeadandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EloadasBeadandoApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/feladat1")
	@ResponseBody
	public String soapTeszt()
			throws MNBArfolyamServiceSoapGetInfoStringFaultFaultMessage,
			MNBArfolyamServiceSoapGetCurrentExchangeRatesStringFaultFaultMessage,
			MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {

		MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
		MNBArfolyamServiceSoap service =
				impl.getCustomBindingMNBArfolyamServiceSoap();

		return service.getInfo()
				+ "<br>"
				+ service.getCurrentExchangeRates()
				+ "<br>"
				+ service.getExchangeRates(
				"2022-08-14",
				"2022-09-14",
				"EUR"
		);
	}
}