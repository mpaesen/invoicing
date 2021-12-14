package model.test;

import model.Address;
import persistency.controller.NumberController;

public class DummyAddress extends Dummy {

    private static final String[] street = {"Riekskensweg", "Dorpstraat", "Hoogstraat", "Achter't heukske", "Tessenstraat", "Bondgenotenlaan", "Diestsestraat", "Meeuwenlaan", "Weg op bocholt", "Brusselsesteenweg", "Mechelsesteenweg", "Gemeentestraat", "Weg op bree", "Weg naar genk", "Peerderstraat",
            "Diestesteenweg"};

    private static final String[] city = {"Bree", "Meeuwen", "Brussel", "Leuven", "Antwerpen", "Genk", "Hasselt", "Gent"};
    private static final String[] zip = {"3960", "3670", "1000", "3000", "2000", "3600", "3500", "9000"};

    private static final String[] type = {"I", "D"};

    public static Address createAddress(final String addRef) {
        final int i = getRandom().nextInt(city.length);
        return new Address(// new java.rmi.dgc.VMID().toString(), //
                // this.idAddress
                // Integer.toHexString(DummyNumber.getNewNumber()),
                NumberController.readLastNumber("Add", 0).toString(), addRef, // this.addRef
                // =
                // addRef;
                street[getRandom().nextInt(street.length)], // this.addStreet =
                // addStreet;
                "" + getRandom().nextInt(999), // this.addNumber = addNumber;
                "" + getRandom().nextInt(20), // this.addBox = addBox;
                zip[i], // this.addZip = addZip;
                city[i], // this.addCity = addCity;
                "Bel", type[getRandom().nextInt(type.length)], // this.addType
                // =
                // addType;
                true // this.active = active;
        );
    }
}
