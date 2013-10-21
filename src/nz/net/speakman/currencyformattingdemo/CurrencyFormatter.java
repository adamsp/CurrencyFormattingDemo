package nz.net.speakman.currencyformattingdemo;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyFormatter {
	
	public static String getFormattedCurrencyString(String isoCurrencyCode, double amount) {
	    // This formats currency values as the user expects to read them (default locale).
	    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	    
	    // This specifies the actual currency that the value is in, and provides the currency symbol.
	    Currency currency = Currency.getInstance(isoCurrencyCode);
	    
	    // Note we don't supply a locale to this method - uses default locale to format the currency symbol.
	    String symbol = currency.getSymbol();
	    
	    // We then tell our formatter to use this symbol.
	    DecimalFormatSymbols decimalFormatSymbols = ((java.text.DecimalFormat) currencyFormat).getDecimalFormatSymbols();
	    decimalFormatSymbols.setCurrencySymbol(symbol);
	    ((java.text.DecimalFormat) currencyFormat).setDecimalFormatSymbols(decimalFormatSymbols);
	    
	    return currencyFormat.format(amount);
	}
	
	public static String getFormattedCurrencyStringForLocale(Locale locale, String isoCurrencyCode, double amount) {
	    // This formats currency values as the user expects to read them (default locale).
	    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
	    
	    // This specifies the actual currency that the value is in, and provides the currency symbol.
	    Currency currency = Currency.getInstance(isoCurrencyCode);
	    
	    // Note we don't supply a locale to this method - uses default locale to format the currency symbol.
	    String symbol = currency.getSymbol(locale);
	    
	    // We then tell our formatter to use this symbol.
	    DecimalFormatSymbols decimalFormatSymbols = ((java.text.DecimalFormat) currencyFormat).getDecimalFormatSymbols();
	    decimalFormatSymbols.setCurrencySymbol(symbol);
	    ((java.text.DecimalFormat) currencyFormat).setDecimalFormatSymbols(decimalFormatSymbols);
	    
	    return currencyFormat.format(amount);
	}
	
	public static String getFormattedCurrencyStringFixed(Locale locale, String isoCurrencyCode, double amount) {
	    // This formats currency values as the user expects to read them in the supplied locale.
	    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
	    
	    // This specifies the actual currency that the value is in, and provides 
	    // the currency symbol that is used
	    Currency currency = Currency.getInstance(isoCurrencyCode);
	    
	    // Our fix is to use the US locale as default for the symbol, unless the currency is USD
	    // and the locale is NOT the US, in which case we know it should be US$.
	    String symbol;
	    if (isoCurrencyCode.equalsIgnoreCase("usd") && !locale.equals(Locale.US)) {
	        symbol = "US$";// currency.getSymbol(Locale.UK);
	    } else {
	        symbol = currency.getSymbol(Locale.US); // US locale has the best symbol formatting table.
	    }
	    
	    // We tell our formatter to use this symbol
	    DecimalFormatSymbols decimalFormatSymbols = ((java.text.DecimalFormat) currencyFormat).getDecimalFormatSymbols();
	    decimalFormatSymbols.setCurrencySymbol(symbol);
	    ((java.text.DecimalFormat) currencyFormat).setDecimalFormatSymbols(decimalFormatSymbols);
	    
	    return currencyFormat.format(amount);
	}

}
