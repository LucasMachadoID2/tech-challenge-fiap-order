db = db.getSiblingDB('tech-chall');

db.products.insertMany([
  {
    name: "Hambúrguer Artesanal",
    description: "Hambúrguer com carne 180g, queijo cheddar e pão brioche",
    image: "https://br.freepik.com/fotos-gratis/hamburguer-de-vista-frontal-em-um-carrinho_9523079.htm#fromView=search&page=1&position=2&uuid=9ecc3827-6e81-4f85-bcac-0728fe83ba6f&query=hamburguer+artesanal",
    price: 3600,
    priceForClient: ,
    category: "LANCHE",
    quantity: 30
  },
  {
    name: "Porção de Batata Frita",
    description: "Porção média de batata frita crocante",
    image: "https://br.freepik.com/fotos-gratis/vista-superior-deliciosas-batatas-fritas-e-molho_21745017.htm#fromView=search&page=1&position=3&uuid=9af82396-46ee-477d-9c07-d65a18279f85&query=por%C3%A7%C3%A3o+de+batata+frita",
    price: 1500,
    priceForClient: 1350,
    category: "ACOMPANHAMENTO",
    quantity: 40
  },
  {
    name: "Refrigerante Lata",
    description: "Lata 350ml de refrigerante (diversos sabores)",
    image: "https://br.freepik.com/imagem-ia-gratis/vista-horizontal-de-uma-maquete-de-uma-lata-de-bebidas-gaseificadas_126949086.htm#fromView=search&page=1&position=10&uuid=c2536a03-1c39-4d59-80bb-dfbd714e6c31&query=refrigerante+lata",
    price: 700,
    priceForClient: 500,
    category: "BEBIDA",
    quantity: 100
  },
  {
    name: "Suco Natural",
    description: "Copo de suco natural 300ml (laranja, limão ou abacaxi)",
    image: "https://br.freepik.com/fotos-gratis/suco-de-laranja-em-um-copo-e-laranja-fresca_7365457.htm#fromView=search&page=1&position=12&uuid=cd46fbb1-0f66-4a62-ad60-c0e91455dde7&query=suco+natural",
    price: 1000,
    priceForClient: ,
    category: "BEBIDA",
    quantity: 60
  },
  {
    name: "Brownie com Sorvete",
    description: "Brownie de chocolate com bola de sorvete de creme",
    image: "https://br.freepik.com/fotos-gratis/brownie-de-chocolate-servido-com-sorvete-de-baunilha-e-morangos_5536627.htm#fromView=search&page=1&position=6&uuid=7e11a676-378e-48f6-946d-18d10db9592f&query=Brownie+com+Sorvete",
    price: 1700,
    priceForClient: ,
    category: "SOBREMESA",
    quantity: 25
  },
  {
    name: "Mini Churros",
    description: "Porção com 6 mini churros recheados com doce de leite",
    image: "https://br.freepik.com/fotos-gratis/churros-de-sobremesa-mexicana-tradicional-com-chocolate_7608568.htm#fromView=search&page=1&position=10&uuid=38628834-5890-470f-bab5-a81e9fa6be1d&query=mini+churros",
    price: 1200,
    priceForClient: 1100,
    category: "SOBREMESA",
    quantity: 35
  }
]);

