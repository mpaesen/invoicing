package model;

public class VATValidator {

	// Method ATcheck(VATnr: string): Boolean;
	// Method BEcheck(VATnr: string): Boolean;
	// Method DEcheck(VATnr: string): Boolean;
	// Method DKcheck(VATnr: string): Boolean;
	// Method FIcheck(VATnr: string): Boolean;
	// Method FRcheck(VATnr: string): Boolean;
	// Method GBcheck(VATnr: string): Boolean;
	// Method ITcheck(VATnr: string): Boolean;
	// Method LUcheck(VATnr: string): Boolean;
	// Method NLcheck(VATnr: string): Boolean;
	// Method NOcheck(VATnr: string): Boolean;
	// Method SEcheck(VATnr: string): Boolean;
	// Method EScheck(VATnr: string): Boolean;
	// Method GRcheck(VATnr: string): Boolean;
	// Method IRcheck(VATnr: string): Boolean;
	// Method PTcheck(VATnr: string): Boolean;
	//
	// implementation

	////////////////////////////////////////////////////////////////////////////
	// //////////////////////
	//
	// VAT CALCULUS HELPER FUNCTIONS
	//
	////////////////////////////////////////////////////////////////////////////
	// //////////////////////

	//
	public static int multiplyAdd(String digit1, String digit2) {
		String s;
		s = String
				.valueOf((Integer.parseInt(digit1) * Integer.parseInt(digit2)));
		if (s.length() > 1) {
			return Character.digit(s.charAt(0), 10)
					+ Character.digit(s.charAt(1), 10);
		} else {
			return Integer.parseInt(s);
		}
	}


	//Belgie.///////////////////////////////////////////////////////////////////
	//original Delphi fucntion
	
//	Function BEcheck(VATnr: string): Boolean;
//	var deelwaarde, rechts2: Integer;
//	begin
//	     If Length(vatnr) <> 9 Then              //Controle op de lengte
//	        Result := false
//	     else
//	         begin
//	              If (strtoint(copy(vatnr,1,1))=0) Or
//	                 (strtoint(copy(vatnr,1,1))=1) Or
//	                 (strtoint(copy(vatnr,1,1))=8) Then
//	                 Result := false                //Controle op eerste cijfer
//	              else
//	                  begin
//	                       deelwaarde:=(97-(strtoint(copy(vatnr,1,7)) Mod 97));  //Bepaal de rest bij deling en haal dat getal van 97 af
//	                       rechts2:=strtoint(copy(vatnr,8,2));                    //Bepaal het controlegetal
//	                       If deelwaarde <> rechts2 Then
//	                          Result := false
//	                       else
//	                          Result := true;
//	                  end;
//	         end;
//	End;

// ////////////////////////
	public static boolean BEcheck(String vatNr) {
		// - Vorm het getal dat bestaat uit de eerste zeven cijfers
		// - Deel dit getal door 97
		// - Indien de rest van de deling gelijk is aan de laatste twee cijfers,
		// dan is het BTWnummer correct.
		//				  0123456789
		// - voorbeeld: BE0886178043
		// - prefix= BE 0
		// - majorFigure= 8861780
		// - control= 43

		String prefix;
		String majorFigure="";
		String control="";

		int rest;
		int lengte = vatNr.length();
		
		switch (lengte) {
		case 9: { // oude BTW nummer
			try {
				Integer.parseInt(vatNr);
			} catch (NumberFormatException exp) {
				return false;
			}
			majorFigure = vatNr.substring(0, 7);
			control = vatNr.substring(7, 9);
			break;
		}

		case 10: { // nieuwe BTW nummer
			try {
				Integer.parseInt(vatNr);
			} catch (NumberFormatException exp) {
				return false;
			}
			majorFigure = vatNr.substring(1, 8);
			control = vatNr.substring(8, 10);
			break;
		}

		case 12: { // ondernemersnummer
			try {
				Integer.parseInt(vatNr.substring(3));
			} catch (NumberFormatException exp) {
				return false;
			}
			prefix = vatNr.substring(0, 3);
			if ((prefix.equalsIgnoreCase("BE0")) ||(prefix.equalsIgnoreCase("BE "))) {
				majorFigure = vatNr.substring(3, 10);
				control = vatNr.substring(10, 12);
			}
			break;
		}
		default:
			return false;
		}
		
		rest = Integer.parseInt(majorFigure) % 97;
		return ((97 -rest) ==  Integer.parseInt(control));

	}

	//Nederland.////////////////////////////////////////////////////////////////
	// ////////////////////////
	public static boolean NLcheck(String vatNr) {
		int i;
		{

			if (vatNr.charAt(9) != 'B') {
				return false;

			}
			i = 0 + 9 * Integer.parseInt(vatNr.substring(0, 1)) + 8
					* Integer.parseInt(vatNr.substring(1, 1)) + 7
					* Integer.parseInt(vatNr.substring(2, 1)) + 6
					* Integer.parseInt(vatNr.substring(3, 1)) + 5
					* Integer.parseInt(vatNr.substring(4, 1)) + 4
					* Integer.parseInt(vatNr.substring(5, 1)) + 3
					* Integer.parseInt(vatNr.substring(6, 1)) + 2
					* Integer.parseInt(vatNr.substring(7, 1));
			i %= 11;
			if (i == 10) {
				return false;
			} else
				return Integer.parseInt(vatNr.substring(8, 1)) == i;

		}
	}
}

//Oostenrijk.///////////////////////////////////////////////////////////////////
// /////////////////////
/*
 * public static boolean ATcheck(Strin VATnr){ var i : integer; { try if
 * VATnr.charAt(0) <> 'U' then { result := false; EXIT; } i :=
 * StrToInt(VATnr[2]) + MultiplyAdd('2',VATnr[3]) + StrToInt(VATnr[4]) +
 * MultiplyAdd('2',VATnr[5]) + StrToInt(VATnr[6]) + MultiplyAdd('2',VATnr[7]) +
 * StrToInt(VATnr[8]); i := 10 - ((i+4) mod 10); if (i = 10) then i := 0; result
 * := (StrToInt(VATnr[9]) = i); except result := false; } End }
 */

//Duitsland.////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean DEcheck(String vatNr){ var a, s, k, b, rechts1, teller:
 * Integer; { If Length(vatNr) <> 9 Then Result := false else { a:=10;
 * s:=(strtoint(copy(vatNr, 1, 1)) + a) Mod 10; If s=0 Then s:=10; For teller:=2
 * To 8 do { k:=strtoint(copy(vatNr, teller, 1)); a:=(2 s) Mod 11; If a=0 Then
 * a:=11; s:=(k + a) Mod 10; If s=0 Then s:=10; } a:=(2 s) Mod 11; If a=0 Then
 * a:=11; b:=11-a; If b=10 Then b:=0; rechts1:=strtoint(copy(vatNr,9, 1)); if
 * b=rechts1 then Result := true else Result := false; }
 * 
 * }
 */
//Denemarken.///////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean DKcheck(String vatNr){ { If Length(vatNr)<>8 Then //
 * Controleer op lengte Result := false else If strtoint(copy(vatNr,1,1))=0 Then
 * Result := false // Controleer op eerste nr <> 0 else If
 * (((strtoint(copy(vatNr,1,1))) 2 + (strtoint(copy(vatNr, 2, 1))) 7 +
 * (strtoint(copy(vatNr, 3, 1))) 6 + (strtoint(copy(vatNr, 4, 1))) 5 +
 * (strtoint(copy(vatNr, 5, 1))) 4 + (strtoint(copy(vatNr, 6, 1))) 3 +
 * (strtoint(copy(vatNr, 7, 1))) 2 + (strtoint(copy(vatNr,8,1)))) Mod 11)=0 Then
 * Result := true else Result := false; }
 */

//Finland.//////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean FIcheck(String vatNr){ var c: Integer; { c := 0; if
 * Length(vatNr) = 8 Then c := (((StrtoInt(copy(vatNr,1,1)) 7) +
 * (StrtoInt(copy(vatNr,2,1)) 9) + (StrtoInt(copy(vatNr,3,1)) 10) +
 * (StrtoInt(copy(vatNr,4,1)) 5) + (StrtoInt(copy(vatNr,5,1)) 8) +
 * (StrtoInt(copy(vatNr,6,1)) 4) + (StrtoInt(copy(vatNr,7,1)) 2) ) Mod 11); if
 * (c = 0) and (StrToInt(copy(vatNr,8,1))=0) Then Result := true else If(c > 2)
 * Then c := 11 - c;
 * 
 * if not Result then If c = strtoint(copy(vatNr,8,1)) Then Result := true else
 * Result := false; }
 */

//Frankrijk.////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean FRcheck(String vatNr){ const FRCharTable : Array[0..33]
 * of char =
 * ('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G'
 * ,'H','J','K','L','M','N', 'P','Q','R','S','T','U','V','W','X','Y','Z'); var
 * i,ic,ic1,ic2,x,y : integer;
 * 
 * public static boolean FindCharInTable(ch : char) : Integer; var i_ : integer;
 * { i_ := 0; while i <= 33 do { if(FRCharTable[i] = ch) then { result := i;
 * EXIT; } i := i + 1; } i := -1; }
 * 
 * { try // Old or new system?? Use a hack method try ic :=
 * StrToInt(Copy(vatNr,1,2)); // if we get here then its the old system.. result
 * := ic = (((StrToInt64(Copy(vatNr,3,9)) 100) + 12) mod 97) except // The new
 * system... ic1 := FindCharInTable(vatNr[1]); ic2 := FindCharInTable(vatNr[2]);
 * if(ic1 < 10) then { i := (ic1 24) + ic2 - 10; end else { i := (ic1 34) + ic2
 * - 100; } X := i mod 11; i := i div 2 + 1; Y := (StrToInt(Copy(vatNr,3,9)) +
 * i) mod 11; result := X = Y; } except result := false; } }
 */

//Engeland./////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean GBcheck(vatNr:string){ var i : integer; { try // What
 * type of VAT number?? if(copy(vatNr,1,2) = 'GD') then { // Goverment //
 * department////////////////////////////////////////////////////// result :=
 * StrToInt(Copy(vatNr,3,3)) < 500; end else if(copy(vatNr,1,2) = 'HA') then {
 * // Health //
 * Authority////////////////////////////////////////////////////////// result :=
 * StrToInt(Copy(vatNr,3,3)) > 499; end else if(Length(vatNr) = 9) then { //
 * Standard////////////////////////////////////////////////////////////////// i
 * := StrToInt(Copy(vatNr,1,7)); if not(((i > 1) and (i < 19999)) or ((i >
 * 1000000) and (i < 9999999))) then { result := false; EXIT; } if
 * StrToInt(Copy(vatNr,8,2)) >= 97 then { result := false; EXIT; } i := 0 + (8
 * StrToInt(vatNr[1])) + (7 StrToInt(vatNr[2])) + (6 StrToInt(vatNr[3])) + (5
 * StrToInt(vatNr[4])) + (4 StrToInt(vatNr[5])) + (3 StrToInt(vatNr[6])) + (2
 * StrToInt(vatNr[7])) + (10 StrToInt(vatNr[8])) + StrToInt(vatNr[9]); result :=
 * (i mod 97) = 0; end else if(Length(vatNr) = 10) then { // Group registred //
 * traders//////////////////////////////////////////////////// i :=
 * StrToInt(Copy(vatNr,1,7)); if not(((i > 1) and (i < 19999)) or ((i > 1000000)
 * and (i < 9999999))) then { result := false; EXIT; } if
 * StrToInt(Copy(vatNr,8,2)) >= 97 then { result := false; EXIT; } if vatNr[10]
 * <> '3' then { result := false; EXIT; } i := 0 + (8 StrToInt(vatNr[1])) + (7
 * StrToInt(vatNr[2])) + (6 StrToInt(vatNr[3])) + (5 StrToInt(vatNr[4])) + (4
 * StrToInt(vatNr[5])) + (3 StrToInt(vatNr[6])) + (2 StrToInt(vatNr[7])) + (10
 * StrToInt(vatNr[8])) + StrToInt(vatNr[9]); result := (i mod 97) = 0; end else
 * if(Length(vatNr) = 12) then { // Isle of //
 * man//////////////////////////////////////////////////////////////// i :=
 * StrToInt(Copy(vatNr,4,7)); if not(((i > 1) and (i < 19999)) or ((i > 1000000)
 * and (i < 9999999))) then { result := false; EXIT; } if
 * StrToInt(Copy(vatNr,11,2)) >= 97 then { result := false; EXIT; } if
 * not((Copy(vatNr,1,3) = '000') or (Copy(vatNr,1,3) = '001')) then { result :=
 * false; EXIT; } i := 0 + (8 StrToInt(vatNr[4 ])) + (7 StrToInt(vatNr[5 ])) +
 * (6 StrToInt(vatNr[6 ])) + (5 StrToInt(vatNr[7 ])) + (4 StrToInt(vatNr[8 ])) +
 * (3 StrToInt(vatNr[9 ])) + (2 StrToInt(vatNr[10])) + (10 StrToInt(vatNr[11]))
 * + StrToInt(vatNr[12]); result := (i mod 97) = 0; end else if(Length(vatNr) =
 * 13) then { // Isle of man, group registred //
 * traders.////////////////////////////////////// i :=
 * StrToInt(Copy(vatNr,4,7)); if not(((i > 1) and (i < 19999)) or ((i > 1000000)
 * and (i < 9999999))) then { result := false; EXIT; } if
 * StrToInt(Copy(vatNr,11,2)) >= 97 then { result := false; EXIT; } if
 * not((Copy(vatNr,1,3) = '000') or (Copy(vatNr,1,3) = '001')) then { result :=
 * false; EXIT; } if vatNr[13] <> '3' then { result := false; EXIT; } i := 0 +
 * (8 StrToInt(vatNr[4 ])) + (7 StrToInt(vatNr[5 ])) + (6 StrToInt(vatNr[6 ])) +
 * (5 StrToInt(vatNr[7 ])) + (4 StrToInt(vatNr[8 ])) + (3 StrToInt(vatNr[9 ])) +
 * (2 StrToInt(vatNr[10])) + (10 StrToInt(vatNr[11])) + StrToInt(vatNr[12]);
 * result := (i mod 97) = 0; end else // invalid result := false; except Result
 * := false; EXIT; } }
 */

//Italie.///////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean ITcheck(String vatNr){ var
 * code,som,somoneven,k,temp,l,r: Integer; { If copy(vatNr,1,7)='0000000' Then
 * // Eerste 7 posities ongelijk // aan 0000000 Result := false else {
 * code:=strtoint(copy(vatNr,8,3)); // De eenheidscode die in het // nummer
 * verwerkt is filteren // en controleren op geldigheid If inttostr(code)=''
 * Then code:=0; If (code>=1) And (code<=100) Or (code=120) Or (code=121) Then {
 * k:=2; som:=0; while k<11 do { temp:=strtoint(copy(vatNr,k,1))2; // Het nummer
 * vanaf // positie 2 tot 10 // doornemen, even // posities // berekenen If
 * inttostr(temp)='' Then code:= 0; If temp > 9 Then {
 * l:=strtoint(copy(inttostr(temp),1,1)); // Getal // groter // dan // 10 // =>
 * // tientallen // en // eenheden // bij // elkaar // optellen
 * r:=strtoint(copy(inttostr(temp),length(inttostr(temp)), 1)); temp:=l + r; }
 * som:=som + temp; // Som bijwerken k:=k + 2; } k:=1; // Initialiseren
 * variabelen somoneven:=0; while k<10 do // Oneven posities tussen positie 1 en
 * 9 { somoneven:=somoneven + strtoint(copy(vatNr,k,1)); k:=k+2; } som:=som +
 * somoneven; // Oneven posities bij de even // posities optellen If
 * ((strtoint(copy(inttostr(som),length(inttostr(som)),1))=0) And
 * (strtoint(copy(vatNr,length(vatNr),1))=0)) Then // controle // op //
 * tientallen Result := true;
 * r:=strtoint(copy(inttostr(som),length(inttostr(som)),1)); // Tientallen // en
 * // eenheden // optellen l:=strtoint(copy(vatNr,length(vatNr),1)); If ((10-r)
 * = l) Then Result := true else Result := false; } } }
 */

//Luxemburg.////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean LUcheck(String vatNr){ var control: Integer; { If
 * Length(vatNr) = 8 Then // Controleer lengte = 8 {
 * control:=strtoint(copy(vatNr,1,6)) Mod 89; // rest bij // deling eerste // 6
 * cijfers // door 87 If control = strtoint(copy(vatNr,7,2)) Then Result := true
 * else Result := false; end else Result := false; }
 */

//Noorwegen.////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean NOcheck(String vatNr) { var nummer: integer; { if
 * length(vatNr)=9 then { nummer:=(strtoint(copy(vatNr,1,1))3)+
 * (strtoint(copy(vatNr,2,1))2)+ (strtoint(copy(vatNr,3,1))7)+
 * (strtoint(copy(vatNr,4,1))6)+ (strtoint(copy(vatNr,5,1))5)+
 * (strtoint(copy(vatNr,6,1))4)+ (strtoint(copy(vatNr,7,1))3)+
 * (strtoint(copy(vatNr,8,1))2); if (11-(nummer mod
 * 11))=(strtoint(copy(vatNr,9,1))) then Result := true else Result := false;
 * end else Result := false; }
 */

//Sweden.///////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean SEcheck(String vatNr){ var k,som,temp,l,r: Integer; {
 * If (Length(vatNr)=12) And (strtoint(copy(vatNr,11,2))<>0) Then { k:=1;
 * som:=0; temp:=0; while k< 10 do { temp:=strtoint(copy(vatNr,k,1)) 2; If temp
 * > 9 Then { l:=strtoint(copy(inttostr(temp),1,1));
 * r:=strtoint(copy(inttostr(temp),length(inttostr(temp)),1)); temp:=l+r; }
 * som:=som+temp; k:=k+2; } k:=2; while k<9 do {
 * temp:=strtoint(copy(vatNr,k,1)); som:=som+temp; k:=k+2; } If
 * ((strtoint((copy(inttostr(som),length(inttostr(som)),1)))=0) And
 * ((strtoint(copy(vatNr,10,1)))=0)) Then Result := true else { r:=som Mod 10;
 * l:=strtoint(copy(vatNr,10,1)); If ((10-r) = l) Then Result := true else
 * Result := false; } end else Result := false; }
 */

//Spanje.///////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean EScheck(String vatNr){ const ESCharTable : Array[1..23]
 * of Char =
 * ('T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q'
 * ,'V','H','L','C','K','E'); var ch : char; i : integer; { try if(Length(vatNr)
 * <> 9) then { Result := false; EXIT; } ch := vatNr[1];
 * 
 * case ch of // Juridical person with profit purpose: 'A' .. 'H' : { i := 0 +
 * MultiplyAdd(vatNr[2],'2'); i := i + strToInt(vatNr[3]); i := i +
 * MultiplyAdd(vatNr[4],'2'); i := i + StrToInt(vatNr[5]); i := i +
 * MultiplyAdd(vatNr[6],'2'); i := i + StrToInt(vatNr[7]); i := i +
 * MultiplyAdd(vatNr[8],'2'); i := 10 - (i mod 10); if(i = 10) then i := 0;
 * result := StrToInt(vatNr[9]) = i; }
 * 
 * // Juridical persons without profit purpose 'N','P','Q','S' : { i := 0 +
 * MultiplyAdd(vatNr[2],'2'); i := i + strToInt(vatNr[3]); i := i +
 * MultiplyAdd(vatNr[4],'2'); i := i + StrToInt(vatNr[5]); i := i +
 * MultiplyAdd(vatNr[6],'2'); i := i + StrToInt(vatNr[7]); i := i +
 * MultiplyAdd(vatNr[8],'2'); i := 10 - (i mod 10); if(i = 10) then i := 0;
 * result := (i - 1 + Ord('A')) = ord(vatNr[9]); }
 * 
 * // Foreigner physical persons smaller than 14 years old or non residents
 * 'K','L','M','X' : { i := 1 +(ExtractNumFromStr(vatNr,2,8) mod 23); Result :=
 * ESCharTable[i] = vatNr[9]; }
 * 
 * // Spanish Physical persons '0' .. '9' : { i := 1
 * +(ExtractNumFromStr(vatNr,1,8) mod 23); Result := ESCharTable[i] = vatNr[9];
 * }
 * 
 * else { Result := false; } } // end case except Result := false; } }
 */

// Greece VAT
//calculus./////////////////////////////////////////////////////////////////////
// /////////
/*
 * public static boolean GRcheck(String vatNr){ var i,il,ipos,imul: integer; {
 * try il := Length(vatNr) - 1; if(il >= 7) and (il <= 8) then { // calculus i
 * := 0; ipos := 1; if(il = 7) then imul := 128 else iMul := 256; while ipos <=
 * il do { i := i + (iMul StrToInt(vatNr[ipos])); iMul := iMul shr 1; ipos :=
 * ipos + 1; } i := i mod 11; if(i = 10) then i := 0; // check control result :=
 * i = StrToInt(vatNr[il + 1]); end else // invalid Result := false; except
 * result := false; } }
 */

//Ireland.//////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean IRcheck(String vatNr){ const IRCharTable : Array[0..22]
 * of char =
 * ('W','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P'
 * ,'Q','R','S','T','U','V'); var i : integer; { try // Old or new system??
 * if(vatNr[2] in ['A'..'Z','+','*']) then { // Old System if(StrToInt(vatNr[1])
 * <= 6) then { Result := false; EXIT; } i := 0 + 7 StrToInt(vatNr[3]) + 6
 * StrToInt(vatNr[4]) + 5 StrToInt(vatNr[5]) + 4 StrToInt(vatNr[6]) + 3
 * StrToInt(vatNr[7]) + 2 StrToInt(vatNr[1]); i := i mod 23; // Check control
 * result := IRCharTable[i] = vatNr[8]; end else { // New System i := 0 + 8
 * StrToInt(vatNr[1]) + 7 StrToInt(vatNr[2]) + 6 StrToInt(vatNr[3]) + 5
 * StrToInt(vatNr[4]) + 4 StrToInt(vatNr[5]) + 3 StrToInt(vatNr[6]) + 2
 * StrToInt(vatNr[7]); i := i mod 23; // Check control result := IRCharTable[i]
 * = vatNr[8]; } except result := false; } }
 */
//Portugal./////////////////////////////////////////////////////////////////////
// ////////////////////
/*
 * public static boolean PTcheck(String vatNr){ var i : integer; { try
 * if(StrToInt(vatNr[1]) <= 0) { { result := false; EXIT; } i := 0 + 9
 * StrToInt(vatNr[1]) + 8 StrToInt(vatNr[2]) + 7 StrToInt(vatNr[3]) + 6
 * StrToInt(vatNr[4]) + 5 StrToInt(vatNr[5]) + 4 StrToInt(vatNr[6]) + 3
 * StrToInt(vatNr[7]) + 2 StrToInt(vatNr[8]); i := 11 - (i mod 11); if(i >= 10)
 * { i := 0; result := i = StrToInt(vatNr[9]); except result := false; } }
 * 
 * }
 */

