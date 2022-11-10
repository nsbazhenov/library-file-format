package com.github.nsbazhenov.fparser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Car(@JacksonXmlProperty(localName = "Date") String date,
                  @JacksonXmlProperty(localName = "BrandName") String brandName,
                  @JacksonXmlProperty(localName = "Price") int price) {
}
