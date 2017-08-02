package cn.edu.ntu.thread;

import cn.edu.ntu.service.ProductService;

public class ClientThread extends Thread {

	private Sequence sequence;
	
	private ProductService productService;
	
	public ClientThread(Sequence sequence){
		this.sequence = sequence;
	}
	
	public ClientThread(ProductService productService){
		this.productService = productService;
	}
	
	@Override
	public void run() {
		productService.addProduct();
	}
}
