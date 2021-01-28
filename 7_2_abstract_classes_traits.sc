trait StringProcessor {
  def process(input: String): String
}

val input = "This is a Wonderful Test!"
// tokenDeleter - в методе process обрабатывает строку, удаляя из неё перечисленные знаки препинания:
// запятая, двоеточие, точка с запятой.
val tokenDeleter = new StringProcessor {
  override def process(input: String): String = input.replaceAll(",:;", "")
}
val step1 = tokenDeleter.process(input)

// toLowerConvertor - заменяет все прописные буквы на строчные.
val toLowerConvertor = new StringProcessor {
  override def process(input: String): String = input.toLowerCase
}
val step2 = toLowerConvertor.process(step1)

// shortener - если строка имеет размер больше 20 символов, он оставляет первые 20 и добавляет к ней "...".
val shortener = new StringProcessor {
  override def process(input: String): String = if (input.length > 20) input.substring(0, 20).concat("...") else input
}
val step3 = shortener.process(step2)
println(step3)